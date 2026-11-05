package com.coderpad.gestionnaire_de_taches.controller;

import com.coderpad.gestionnaire_de_taches.domain.TaskMapper;
import com.coderpad.gestionnaire_de_taches.domain.TaskNotFoundException;
import com.coderpad.gestionnaire_de_taches.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping
    ResponseEntity<List<TaskDto>> getTasks() {
        var tasks = taskService.getAllTasks()
                .stream()
                .map(taskMapper::toDto)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        var task = taskService.getTaskById(id);
        if (task == null) {
            throw new TaskNotFoundException("Task with id " + id + " not found");
        }
        return ResponseEntity.ok(taskMapper.toDto(task));
    }

    @PostMapping
    ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        var task = taskMapper.fromDto(taskDto);
        var createdTask = taskService.createTask(task);
        return ResponseEntity.ok(taskMapper.toDto(createdTask));
    }

    @PutMapping("/{id}")
    ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        var task = taskMapper.fromDto(taskDto);
        var updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(taskMapper.toDto(updatedTask));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
