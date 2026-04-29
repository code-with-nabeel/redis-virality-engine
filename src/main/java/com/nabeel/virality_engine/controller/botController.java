package com.nabeel.virality_engine.controller;



import com.nabeel.virality_engine.dto.bot.BotRequestDto;
import com.nabeel.virality_engine.dto.bot.BotResponseDto;
import com.nabeel.virality_engine.service.BotService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bots")

@AllArgsConstructor
public class botController {

    private final BotService botService;

    // CREATE
    @PostMapping
    public ResponseEntity<BotResponseDto> createBot(@Valid @RequestBody BotRequestDto dto) {
        return   new ResponseEntity<>(botService.createBot(dto), HttpStatus.CREATED);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<BotResponseDto> getBot(@PathVariable Long id) {
        return ResponseEntity.ok(botService.getBotById(id));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<BotResponseDto>> getAllBots() {
        return ResponseEntity.ok(botService.getAllBots());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<BotResponseDto> updateBot(
            @PathVariable Long id,
            @Valid @RequestBody BotRequestDto dto) {

        return ResponseEntity.ok(botService.updateBot(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBot(@PathVariable Long id) {
        botService.deleteBot(id);
        return ResponseEntity.ok("Bot deleted successfully");
    }
}
