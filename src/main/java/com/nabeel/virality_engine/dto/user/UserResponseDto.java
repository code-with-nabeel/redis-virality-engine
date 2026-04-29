package com.nabeel.virality_engine.dto.user;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserResponseDto {
    private Long id;
    private String username;
    private Boolean isPremium;
}
