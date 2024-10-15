package com.example.tripplanningservice.controller;

import com.example.tripplanningservice.dto.ShortTaskDTO;
import com.example.tripplanningservice.dto.TaskDTO;
import com.example.tripplanningservice.service.TaskService;
import com.example.tripplanningservice.service.WaypointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/route/{routeId}")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/task")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO createRouteTask(
            @RequestBody ShortTaskDTO shortTaskDTO,
            @PathVariable long routeId,
            Authentication authentication
    ){
        String currentUsername = authentication.getName();
        return taskService.createRouteTask(currentUsername, shortTaskDTO, routeId);
    }

    @PostMapping("/task/{taskId}")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO updateRouteTask(
            @RequestBody ShortTaskDTO shortTaskDTO,
            @PathVariable long routeId,
            @PathVariable long taskId,
            Authentication authentication
    ){
        String currentUsername = authentication.getName();
        if (!taskService.isOwner(taskId, currentUsername)) {
            throw new AccessDeniedException("You are not allowed to update this task");
        }
        return taskService.updateTask(shortTaskDTO, taskId);
    }

    @DeleteMapping("/task/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteRouteTask(
            @PathVariable long routeId,
            @PathVariable long taskId,
            Authentication authentication) {
        String currentUsername = authentication.getName();
        if (!taskService.isOwner(taskId, currentUsername)) {
            throw new AccessDeniedException("You are not allowed to update this task");
        }
        boolean isDeleted = taskService.deleteTask(taskId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/waypoint/{waypointId}/task")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO createWaypointTask(
            @RequestBody ShortTaskDTO shortTaskDTO,
            @PathVariable long routeId,
            @PathVariable long waypointId,
            Authentication authentication
    ){
        String currentUsername = authentication.getName();
        return taskService.createWaypointTask(currentUsername, shortTaskDTO, routeId, waypointId);
    }

    @PostMapping("/waypoint/{waypointId}/task/{taskId}")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO updateWaypointTask(
            @RequestBody ShortTaskDTO shortTaskDTO,
            @PathVariable long routeId,
            @PathVariable long waypointId,
            @PathVariable long taskId,
            Authentication authentication
    ){
        String currentUsername = authentication.getName();
        if (!taskService.isOwner(taskId, currentUsername)) {
            throw new AccessDeniedException("You are not allowed to update this task");
        }
        return taskService.updateTask(shortTaskDTO, taskId);
    }

    @DeleteMapping("/waypoint/{waypointId}/task/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteWaypointTask(
            @PathVariable long routeId,
            @PathVariable long waypointId,
            @PathVariable long taskId,
            Authentication authentication) {
        String currentUsername = authentication.getName();
        if (!taskService.isOwner(taskId, currentUsername)) {
            throw new AccessDeniedException("You are not allowed to update this task");
        }
        boolean isDeleted = taskService.deleteTask(taskId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
