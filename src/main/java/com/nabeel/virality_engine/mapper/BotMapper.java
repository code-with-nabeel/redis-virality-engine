package com.nabeel.virality_engine.mapper;

import com.nabeel.virality_engine.dto.bot.BotRequestDto;
import com.nabeel.virality_engine.dto.bot.BotResponseDto;
import com.nabeel.virality_engine.entity.Bot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BotMapper {

    Bot toEntity(BotRequestDto dto);

    BotResponseDto toDto(Bot bot);
}
