package com.coderpad.gestionnaire_de_taches.domain;

import com.coderpad.gestionnaire_de_taches.controller.TaskDto;
import com.coderpad.gestionnaire_de_taches.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    // 1. Pour toDomain : On convertit explicitement le LocalDateTime en String
    //@Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    Task toDomain(TaskEntity taskEntity);

    // 2. Pour toEntity : On ignore l'id généré par la BDD et on reconvertit la String en LocalDateTime
    //@Mapping(ignore = true, target = "id")
    //@Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    TaskEntity toEntity(Task task);

    TaskDto toDto(Task task);
    Task fromDto(TaskDto taskDto);
}
