package com.nabeel.virality_engine.service;

import com.nabeel.virality_engine.dto.user.UserRequestDto;
import com.nabeel.virality_engine.dto.user.UserResponseDto;
import com.nabeel.virality_engine.entity.User;
import com.nabeel.virality_engine.exception.ResourceNotFoundException;
import com.nabeel.virality_engine.mapper.UserMapper;
import com.nabeel.virality_engine.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    //CREATE
    public UserResponseDto createUser(UserRequestDto dto){
      User user=userMapper.toEntity(dto);
      User save=userRepository.save(user);
      return userMapper.toDto(save);
   }

   //GET BY ID
    public UserResponseDto getById(Long id){
        User user=userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toDto(user);
    }

    //Get All
    public List<UserResponseDto>getAllUser(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    //UPDATE
    public UserResponseDto  updateUser(Long id,UserRequestDto dto){
        User user=userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        user.setUsername(dto.getUsername());
        user.setIsPremium(dto.getIsPremium());
        User update=userRepository.save(user);
        return userMapper.toDto(update);
    }

    //delete
    public void deleteUser(Long id){
        User user=userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }
}

