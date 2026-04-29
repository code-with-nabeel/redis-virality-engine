package com.nabeel.virality_engine.service;


import com.nabeel.virality_engine.dto.post.PostRequestDto;
import com.nabeel.virality_engine.dto.post.PostResponseDto;
import com.nabeel.virality_engine.entity.Post;
import com.nabeel.virality_engine.entity.User;
import com.nabeel.virality_engine.exception.ResourceNotFoundException;
import com.nabeel.virality_engine.mapper.PostMapper;
import com.nabeel.virality_engine.repository.PostRepository;
import com.nabeel.virality_engine.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final  UserRepository userRepository;
    private final PostMapper postMapper;

    //CREATE POST
    public PostResponseDto createPost(PostRequestDto dto){

        // 1. USER FETCH (RELATION VALIDATION)
        User user=userRepository.findById(dto.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id :"+ dto.getUserId()));
        // 2. DTO → ENTITY
        Post post=postMapper.toEntity(dto);

        //  3. SET RELATION (MOST IMPORTANT LINE)
        post.setUser(user);

        // 4. SAVE
        Post savePost=postRepository.save(post);

        return postMapper.toDto(savePost);
    }

    //GET POST BY ID
    public PostResponseDto getPostById(Long id){
        Post post=postRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Post not found with id: " + id));
        return postMapper.toDto(post);
    }

    //GET ALL POST
    public List<PostResponseDto> getAllPost(){
        return postRepository.findAll()
                .stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());

    }
    //GET POST BY USERID
    public List<PostResponseDto>getPostsByUserId(Long userId){
        List<Post>posts=postRepository.findByUserId(userId);
        if(posts.isEmpty()){
            throw new ResourceNotFoundException("No posts found for userId: " + userId);
        }
        return posts.stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    //update
    public PostResponseDto updatePost(PostRequestDto dto,Long id){
        // 1. Existing post fetch
        Post post=postRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Post not found"));

        // 2. Optional: user validate (agar author change allow karna hai)
        User user=userRepository.findById(dto.getUserId())
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));

        // 3. Update fields
        post.setContent(dto.getContent());

        // 🔥 relation update (important if author changes)
        post.setUser(user);

        // 4. Save
        Post updated = postRepository.save(post);

        return postMapper.toDto(updated);

    }


    //delete
    public void deletePost(Long id){
        Post post=postRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Post not found with id :" + id ));
         postRepository.delete(post);
    }
}
