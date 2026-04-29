package com.nabeel.virality_engine.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    @NotNull
    private Long userId;

    @NotBlank
    private String content;
}
