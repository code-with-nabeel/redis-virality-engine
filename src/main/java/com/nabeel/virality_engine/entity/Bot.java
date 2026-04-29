package com.nabeel.virality_engine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String personaDescription;

    @OneToMany(mappedBy = "bot", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments;
}