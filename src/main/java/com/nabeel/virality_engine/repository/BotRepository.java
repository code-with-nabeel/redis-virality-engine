package com.nabeel.virality_engine.repository;

import com.nabeel.virality_engine.entity.Bot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotRepository extends JpaRepository<Bot,Long> {

}
