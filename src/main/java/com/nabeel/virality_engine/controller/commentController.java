package com.nabeel.virality_engine.controller;

import com.nabeel.virality_engine.dto.comment.CommentRequestDto;
import com.nabeel.virality_engine.dto.comment.CommentResponseDto;
import com.nabeel.virality_engine.service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comments")
public class commentController {
    private final CommentService commentService;

    //ADD COMMENT
    @PostMapping("/{postId}")
    public ResponseEntity<CommentResponseDto>addComment(@PathVariable Long postId, @Valid
                                                        @RequestBody CommentRequestDto dto){
        return new ResponseEntity<>(commentService.createComment(postId,dto), HttpStatus.CREATED);

    }
    //  GET COMMENTS BY POST
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponseDto>> getComments(
            @PathVariable Long postId) {

        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }
}
