package com.darshan.ai.agentplatform.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String userMessage;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String aiResponse;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}