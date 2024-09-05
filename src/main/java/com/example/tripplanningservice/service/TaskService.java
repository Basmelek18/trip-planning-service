package com.example.tripplanningservice.service;

import com.example.tripplanningservice.dto.ShortTaskDTO;
import com.example.tripplanningservice.dto.TaskMapper;
import com.example.tripplanningservice.exception.RouteNotFoundException;
import com.example.tripplanningservice.model.Route;
import com.example.tripplanningservice.model.Task;
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
    public ShortTaskDTO createRouteTask(ShortTaskDTO shortTaskDTO, long id) {
        Route route = routeRepository.findById(id);
        if (route == null) {
            throw new RouteNotFoundException("Route doesn't found");
        }
        Task task = new Task();
        task.setRoute(route);
        task.setDescription(shortTaskDTO.getDescription());
        task.setCompleted(false);
        taskRepository.save(task);
        return TaskMapper.toShortTaskDTO(task);
    }


}
