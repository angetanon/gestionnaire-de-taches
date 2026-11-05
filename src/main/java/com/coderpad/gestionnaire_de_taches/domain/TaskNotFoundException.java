package com.coderpad.gestionnaire_de_taches.domain;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
