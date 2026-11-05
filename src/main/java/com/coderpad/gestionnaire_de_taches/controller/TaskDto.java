package com.coderpad.gestionnaire_de_taches.controller;

import com.coderpad.gestionnaire_de_taches.domain.Priority;
import com.coderpad.gestionnaire_de_taches.domain.Status;
import lombok.NonNull;

import java.time.LocalDateTime;

public record TaskDto (
    Long id,
    @NonNull
    String title,
    String description,
    Status status,
    Priority priority,
    @NonNull LocalDateTime createdAt
) {

}
