package com.coderpad.gestionnaire_de_taches.controller;

import com.coderpad.gestionnaire_de_taches.domain.TaskNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(TaskNotFoundException.class)
    public ProblemDetail handleInvalidInputException(TaskNotFoundException e, WebRequest request) {
        ProblemDetail problemDetail
                = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setInstance(URI.create("discount"));
        return problemDetail;
    }
}
