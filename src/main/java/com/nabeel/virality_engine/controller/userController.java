package com.nabeel.virality_engine.controller;

import com.nabeel.virality_engine.dto.user.UserRequestDto;
import com.nabeel.virality_engine.dto.user.UserResponseDto;
import com.nabeel.virality_engine.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class userController {

    private UserService userService;

    //CREATE
    @PostMapping
    public ResponseEntity<UserResponseDto>createUser(@Valid  @RequestBody UserRequestDto dto){
        return new ResponseEntity<>(userService.createUser(dto),HttpStatus.CREATED);
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto>getById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getById(id));
    }

    //GET ALL
    @GetMapping
    public ResponseEntity<List<UserResponseDto>>getAll(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto>updateUser(@PathVariable Long id, @Valid
                                                     @RequestBody UserRequestDto dto){
        return ResponseEntity.ok(userService.updateUser(id,dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

}
