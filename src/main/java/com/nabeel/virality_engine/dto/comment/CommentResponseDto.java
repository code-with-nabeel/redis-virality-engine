package com.nabeel.virality_engine.dto.comment;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentResponseDto {
    private Long id;
    private Long postId;
    private Long authorId;
    private String authorType;
    private String content;
    private int depthLevel;
    private LocalDateTime createdAt;
}