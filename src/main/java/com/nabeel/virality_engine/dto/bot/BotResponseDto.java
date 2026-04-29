package com.nabeel.virality_engine.dto.bot;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BotResponseDto {
    private Long id;
    private String name;
    private String personaDescription;
}
