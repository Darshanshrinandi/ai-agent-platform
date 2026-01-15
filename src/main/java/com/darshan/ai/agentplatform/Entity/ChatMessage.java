package com.darshan.ai.agentplatform.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 4000)
    private String userMessage;

    @Column(nullable = false, length = 4000)
    private String aiResponse;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
