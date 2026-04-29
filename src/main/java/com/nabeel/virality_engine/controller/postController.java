package com.nabeel.virality_engine.controller;

import com.nabeel.virality_engine.dto.post.PostRequestDto;
import com.nabeel.virality_engine.dto.post.PostResponseDto;
import com.nabeel.virality_engine.service.PostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/posts")
public class postController {

    private final PostService postService;

    //CREATE POST
    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@Valid  @RequestBody PostRequestDto dto){
       return new ResponseEntity<>(postService.createPost(dto),HttpStatus.CREATED);
    }

    //GET ALL POST
    @GetMapping
    public ResponseEntity<List<PostResponseDto>>getAllPost(){
        return ResponseEntity.ok(postService.getAllPost());
    }

    //GET POST BY ID
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto>getPostById(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //GET POST BY USERID

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponseDto>>getPostByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(postService.getPostsByUserId(userId));
    }

    //UPDATE POST
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto>updatePost(@Valid @RequestBody PostRequestDto dto,
                                                     @PathVariable Long id){
       return new ResponseEntity<>(postService.updatePost(dto,id),HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.ok("Post deleted successfully");
    }
}
