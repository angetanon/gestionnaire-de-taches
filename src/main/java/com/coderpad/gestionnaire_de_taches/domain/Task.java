package com.coderpad.gestionnaire_de_taches.domain;

import lombok.NonNull;

import java.time.LocalDateTime;

public record Task(
        Long id,
        @NonNull String title,
        String description,
        Status status,
        Priority priority,
        @NonNull LocalDateTime createdAt
) {}
