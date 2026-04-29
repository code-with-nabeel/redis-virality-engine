package com.nabeel.virality_engine.mapper;

import com.nabeel.virality_engine.dto.comment.CommentRequestDto;
import com.nabeel.virality_engine.dto.comment.CommentResponseDto;
import com.nabeel.virality_engine.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "postId",
            expression = "java(comment.getPost() != null ? comment.getPost().getId() : null)")

    @Mapping(target = "authorId",
            expression = "java(comment.getUser() != null ? comment.getUser().getId() : (comment.getBot() != null ? comment.getBot().getId() : null))")

    @Mapping(target = "authorType",
            expression = "java(comment.getUser() != null ? \"User\" : (comment.getBot() != null ? \"Bot\" : null))")

    CommentResponseDto toDto(Comment comment);

    @Mapping(target = "post", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "bot", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Comment toEntity(CommentRequestDto dto);
}