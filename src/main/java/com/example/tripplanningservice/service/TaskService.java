package com.example.tripplanningservice.service;

import com.example.tripplanningservice.dto.ShortTaskDTO;
import com.example.tripplanningservice.dto.TaskDTO;
import com.example.tripplanningservice.dto.TaskMapper;
import com.example.tripplanningservice.exception.RouteNotFoundException;
import com.example.tripplanningservice.model.Route;
import com.example.tripplanningservice.model.Task;
import com.example.tripplanningservice.model.Waypoint;
import com.example.tripplanningservice.repository.RouteRepository;
import com.example.tripplanningservice.repository.TaskRepository;
import com.example.tripplanningservice.repository.WaypointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {
    private final RouteRepository routeRepository;
    private final WaypointRepository waypointRepository;
    private final TaskRepository taskRepository;

    @Transactional
    public TaskDTO createRouteTask(ShortTaskDTO shortTaskDTO, long route_id) {
        Route route = routeRepository.findById(route_id);
        if (route == null) {
            throw new RouteNotFoundException("Route doesn't found");
        }
        Task task = new Task();
        task.setRoute(route);
        task.setDescription(shortTaskDTO.getDescription());
        task.setCompleted(false);
        taskRepository.save(task);
        return TaskMapper.toTaskDTO(task);
    }

    @Transactional
    public TaskDTO createWaypointTask(ShortTaskDTO shortTaskDTO, long route_id, long waypoint_id) {
        Waypoint waypoint = waypointRepository.findById(waypoint_id);
        if (waypoint == null) {
            throw new RouteNotFoundException("Waypoint doesn't found");
        }
        Route route = routeRepository.findById(route_id);
        Task task = new Task();
        task.setRoute(route);
        task.setWaypoint(waypoint);
        task.setDescription(shortTaskDTO.getDescription());
        task.setCompleted(false);
        taskRepository.save(task);
        return TaskMapper.toTaskDTO(task);
    }


}
