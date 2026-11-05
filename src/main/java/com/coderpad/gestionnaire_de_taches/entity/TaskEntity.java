package com.coderpad.gestionnaire_de_taches.entity;

import com.coderpad.gestionnaire_de_taches.domain.Priority;
import com.coderpad.gestionnaire_de_taches.domain.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Data
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Priority priority;

    @Column(name = "createdat", nullable = false, updatable = false)
    LocalDateTime createdAt;
}
