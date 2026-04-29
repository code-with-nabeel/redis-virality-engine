package com.nabeel.virality_engine.mapper;

import com.nabeel.virality_engine.dto.post.PostRequestDto;
import com.nabeel.virality_engine.dto.post.PostResponseDto;
import com.nabeel.virality_engine.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    // ENTITY → DTO
    @Mapping(target = "userId",
            expression = "java(post.getUser() != null ? post.getUser().getId() : null)")
    PostResponseDto toDto(Post post);

    // DTO → ENTITY
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Post toEntity(PostRequestDto dto);
}