package com.coderpad.gestionnaire_de_taches.service;

import com.coderpad.gestionnaire_de_taches.domain.Priority;
import com.coderpad.gestionnaire_de_taches.domain.Status;
import com.coderpad.gestionnaire_de_taches.domain.Task;
import com.coderpad.gestionnaire_de_taches.domain.TaskMapper;
import com.coderpad.gestionnaire_de_taches.entity.TaskEntity;
import com.coderpad.gestionnaire_de_taches.repository.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @Test
    @DisplayName("getAllTasks retourne les tâches mappées vers le domaine")
    void getAllTasks_shouldReturnMappedTasks() {

        // Given
        var taskEntities = mockTaks();

        when(taskRepository.findAll()).thenReturn(taskEntities);
        when(taskMapper.toDomain(any(TaskEntity.class))).thenReturn(
                new Task(1L, "Task 1", "Description 1", Status.TODO, Priority.HIGH, LocalDateTime.now()),
                new Task(2L, "Task 2", "Description 2", Status.DONE, Priority.LOW, LocalDateTime.now())
        );

        // When
        var tasks = taskService.getAllTasks();

        // Then
        assertNotNull(tasks);
        assertEquals(2, tasks.size());
        verify(taskMapper, times(2)).toDomain(any(TaskEntity.class));

    }

    @Test
    @DisplayName("getTaskById retourne la tâche mappée vers le domaine")
    void getTaskById_shouldReturnMappedTask() {
        // Given
        Long taskId = 1L;
        TaskEntity taskEntity = mockTaks().get(0);

        when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.of(taskEntity));
        when(taskMapper.toDomain(any(TaskEntity.class))).thenReturn(
                new Task(1L, "Task 1", "Description 1", Status.TODO, Priority.HIGH, LocalDateTime.now())
        );

        // When
        var task = taskService.getTaskById(taskId);

        // Then
        assertNotNull(task);
        assertEquals(taskId, task.id());
        verify(taskMapper, times(1)).toDomain(any(TaskEntity.class));
    }

    @Test
    @DisplayName("getTaskById retourne null lorsque la tâche n'est pas trouvée")
    void getTaskById_shouldReturnNullWhenNotFound() {
        // Given
        Long taskId = 1L;

        when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.empty());

        // When
        var task = taskService.getTaskById(taskId);

        // Then
        assertNull(task);
        verify(taskMapper, times(0)).toDomain(any(TaskEntity.class));
    }

    private static List<TaskEntity> mockTaks() {
        TaskEntity taskEntity1 = new TaskEntity();
        taskEntity1.setId(1L);
        taskEntity1.setTitle("Task 1");
        taskEntity1.setDescription("Description 1");
        taskEntity1.setStatus(Status.TODO);
        taskEntity1.setPriority(Priority.HIGH);
        taskEntity1.setCreatedAt(LocalDateTime.now());

        TaskEntity taskEntity2 = new TaskEntity();
        taskEntity2.setId(2L);
        taskEntity2.setTitle("Task 2");
        taskEntity2.setDescription("Description 2");
        taskEntity2.setStatus(Status.DONE);
        taskEntity2.setPriority(Priority.LOW);
        taskEntity2.setCreatedAt(LocalDateTime.now());

        return List.of(taskEntity1, taskEntity2);
    }
}

