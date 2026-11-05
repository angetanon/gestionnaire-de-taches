package com.coderpad.gestionnaire_de_taches.service;

import com.coderpad.gestionnaire_de_taches.domain.Task;
import com.coderpad.gestionnaire_de_taches.domain.TaskMapper;
import com.coderpad.gestionnaire_de_taches.domain.TaskNotFoundException;
import com.coderpad.gestionnaire_de_taches.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<Task> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toDomain)
                .toList();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toDomain)
                .orElse(null);
    }

    public Task createTask(Task task) {
        var entity = taskMapper.toEntity(task);
        var savedEntity = taskRepository.save(entity);
        return taskMapper.toDomain(savedEntity);
    }

    public Task updateTask(Long id, Task task) {
        var entity = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));;
        entity.setTitle(task.title());
        entity.setDescription(task.description());
        entity.setStatus(task.status());
        entity.setPriority(task.priority());
        var updatedEntity = taskRepository.save(entity);
        return taskMapper.toDomain(updatedEntity);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
