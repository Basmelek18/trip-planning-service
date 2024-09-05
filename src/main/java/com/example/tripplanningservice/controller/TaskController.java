package com.example.tripplanningservice.controller;

import com.example.tripplanningservice.dto.ShortTaskDTO;
import com.example.tripplanningservice.dto.TaskDTO;
import com.example.tripplanningservice.service.TaskService;
import com.example.tripplanningservice.service.WaypointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/route/{routeId}")
public class TaskController {
    private final WaypointService waypointService;
    private final TaskService taskService;

    @PostMapping("/task")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO createRouteTask(@RequestBody ShortTaskDTO shortTaskDTO, @PathVariable long routeId) {
        return taskService.createRouteTask(shortTaskDTO, routeId);
    }

    @PostMapping("/waypoint/{waypointId}/task")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO createWaypointTask(@RequestBody ShortTaskDTO shortTaskDTO, @PathVariable long routeId, @PathVariable long waypointId) {
        return taskService.createWaypointTask(shortTaskDTO, routeId, waypointId);
    }
}
