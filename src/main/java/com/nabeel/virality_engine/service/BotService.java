package com.nabeel.virality_engine.service;

import com.nabeel.virality_engine.dto.bot.BotRequestDto;
import com.nabeel.virality_engine.dto.bot.BotResponseDto;
import com.nabeel.virality_engine.entity.Bot;
import com.nabeel.virality_engine.exception.ResourceNotFoundException;
import com.nabeel.virality_engine.mapper.BotMapper;
import com.nabeel.virality_engine.repository.BotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BotService {

    private final BotRepository botRepository;
    private final BotMapper botMapper;

    // CREATE
    public BotResponseDto createBot(BotRequestDto dto) {
        Bot bot = botMapper.toEntity(dto);
        Bot saved = botRepository.save(bot);
        return botMapper.toDto(saved);
    }

    // GET BY ID
    public BotResponseDto getBotById(Long id) {
        Bot bot = botRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bot not found"));
        return botMapper.toDto(bot);
    }

    // GET ALL
    public List<BotResponseDto> getAllBots() {
        return botRepository.findAll()
                .stream()
                .map(botMapper::toDto)
                .collect(Collectors.toList());
    }

    // UPDATE
    public BotResponseDto updateBot(Long id, BotRequestDto dto) {
        Bot bot = botRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bot not found"));

        bot.setName(dto.getName());
        bot.setPersonaDescription(dto.getPersonaDescription());

        Bot updated = botRepository.save(bot);
        return botMapper.toDto(updated);
    }

    // DELETE
    public void deleteBot(Long id) {
        Bot bot = botRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bot not found"));

        botRepository.delete(bot);
    }
}
