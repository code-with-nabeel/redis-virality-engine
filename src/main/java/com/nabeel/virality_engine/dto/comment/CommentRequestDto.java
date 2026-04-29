package com.nabeel.virality_engine.dto.comment;

import lombok.Data;

@Data
public class CommentRequestDto {
    private Long authorId;
    private String authorType;
    private String content;
    private Long parentCommentId;
    private Long targetUserId;
}