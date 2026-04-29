package com.nabeel.virality_engine.service;

import com.nabeel.virality_engine.dto.comment.CommentRequestDto;
import com.nabeel.virality_engine.dto.comment.CommentResponseDto;
import com.nabeel.virality_engine.entity.Bot;
import com.nabeel.virality_engine.entity.Comment;
import com.nabeel.virality_engine.entity.Post;
import com.nabeel.virality_engine.entity.User;
import com.nabeel.virality_engine.exception.BadRequestException;
import com.nabeel.virality_engine.exception.ResourceNotFoundException;
import com.nabeel.virality_engine.mapper.CommentMapper;
import com.nabeel.virality_engine.repository.BotRepository;
import com.nabeel.virality_engine.repository.CommentRepository;
import com.nabeel.virality_engine.repository.PostRepository;
import com.nabeel.virality_engine.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BotRepository botRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final RedisService redisService;
    private final CommentMapper commentMapper;

    // ✅ CREATE COMMENT
    public CommentResponseDto createComment(Long postId, CommentRequestDto dto){

        // 1. POST VALIDATION
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        // 2. MAP DTO → ENTITY
        Comment comment = commentMapper.toEntity(dto);
        comment.setPost(post);

        // 3. AUTHOR LOGIC
        if(dto.getAuthorType().equalsIgnoreCase("USER")){

            User user = userRepository.findById(dto.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            comment.setUser(user);
            comment.setBot(null);

            // 🔥 SCORE +50
            redisService.incrementScore(postId, 50);

        }
        else if(dto.getAuthorType().equalsIgnoreCase("BOT")){

            Bot bot = botRepository.findById(dto.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Bot not found"));

            comment.setBot(bot);
            comment.setUser(null);

            Long userId = dto.getTargetUserId();

            if(userId == null){
                throw new BadRequestException("targetUserId is required for BOT");
            }

            // 🔥 1. COOLDOWN CHECK
            if(redisService.isCooldownActive(bot.getId(), userId)){
                throw new BadRequestException("Cooldown active. Try after 10 minutes");
            }

            // 🔥 2. BOT LIMIT CHECK (NO increment yet)
            String key = "post:" + postId + ":bot_count";
            Object existing = redisService.getValue(key);

            Long count = existing == null ? 0 : Long.parseLong(existing.toString());

            if(count >= 100){
                throw new BadRequestException("Bot limit exceeded (100 max)");
            }

            // 🔥 3. SAFE INCREMENT
            redisService.incrementBotCount(postId);

            // 🔥 4. SET COOLDOWN
            redisService.setCooldown(bot.getId(), userId);

            // 🔥 5. SCORE +1
            redisService.incrementScore(postId, 1);
        }
        else {
            throw new BadRequestException("Invalid author type");
        }

        // 🔥 DEPTH LOGIC
        if (dto.getParentCommentId() != null) {

            Comment parent = commentRepository.findById(dto.getParentCommentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent comment not found"));

            if (!parent.getPost().getId().equals(postId)) {
                throw new BadRequestException("Parent comment does not belong to this post");
            }

            int depth = parent.getDepthLevel() + 1;

            if(depth > 20){
                throw new BadRequestException("Max depth (20) exceeded");
            }

            comment.setParent(parent);
            comment.setDepthLevel(depth);
        }

        // 6. SAVE
        Comment saved = commentRepository.save(comment);
        return commentMapper.toDto(saved);
    }

    // ✅ GET COMMENTS
    public List<CommentResponseDto> getCommentsByPost(Long postId) {

        postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        List<Comment> comments = commentRepository.findByPostId(postId);

        if (comments.isEmpty()) {
            throw new ResourceNotFoundException("No comments found for this post");
        }

        return comments.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }
}