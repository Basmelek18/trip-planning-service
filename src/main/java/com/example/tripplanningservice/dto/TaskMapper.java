package com.example.tripplanningservice.dto;

import com.example.tripplanningservice.model.Task;

public class TaskMapper {
    public static TaskDTO toTaskDTO(Task task) {
        return TaskDTO.builder()
                .description(task.getDescription())
                .completed(task.getCompleted())
                .build();
    }

    public static ShortTaskDTO toShortTaskDTO(Task task) {
        return ShortTaskDTO.builder()
                .description(task.getDescription())
                .build();
    }
}
