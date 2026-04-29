package com.nabeel.virality_engine.mapper;

import com.nabeel.virality_engine.dto.user.UserRequestDto;
import com.nabeel.virality_engine.dto.user.UserResponseDto;
import com.nabeel.virality_engine.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "isPremium", target = "isPremium")
    User toEntity(UserRequestDto dto);
    UserResponseDto toDto(User user);
}
