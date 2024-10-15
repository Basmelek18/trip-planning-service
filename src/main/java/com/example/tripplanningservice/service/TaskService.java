package com.example.tripplanningservice.service;

import com.example.tripplanningservice.dto.ShortTaskDTO;
import com.example.tripplanningservice.dto.TaskDTO;
import com.example.tripplanningservice.dto.TaskMapper;
import com.example.tripplanningservice.exception.NotFoundException;
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
    public TaskDTO createRouteTask(String username, ShortTaskDTO shortTaskDTO, long routeId) {
        Route route = routeRepository.findById(routeId);
        if (route == null) {
            throw new NotFoundException("Route doesn't found");
        }
        Task task = new Task();
        task.setRoute(route);
        task.setAuthorUsername(username);
        task.setDescription(shortTaskDTO.getDescription());
        task.setCompleted(false);
        taskRepository.save(task);
        return TaskMapper.toTaskDTO(task);
    }


    @Transactional
    public TaskDTO createWaypointTask(String username, ShortTaskDTO shortTaskDTO, long routeId, long waypointId) {
        Waypoint waypoint = waypointRepository.findById(waypointId);
        if (waypoint == null) {
            throw new NotFoundException("Waypoint doesn't found");
        }
        Route route = routeRepository.findById(routeId);
        if (route == null) {
            throw new NotFoundException("Route doesn't found");
        }
        Task task = new Task();
        task.setRoute(route);
        task.setWaypoint(waypoint);
        task.setAuthorUsername(username);
        task.setDescription(shortTaskDTO.getDescription());
        task.setCompleted(false);
        taskRepository.save(task);
        return TaskMapper.toTaskDTO(task);
    }

    @Transactional
    public TaskDTO updateTask(ShortTaskDTO shortTaskDTO, long taskId) {
        Task task = taskRepository.findById(taskId);
        if (task == null) {
            throw new NotFoundException("Task doesn't found");
        }
        task.setDescription(shortTaskDTO.getDescription());
        taskRepository.save(task);
        return TaskMapper.toTaskDTO(task);
    }

    @Transactional
    public boolean deleteTask(long task_id) {
        if (taskRepository.existsById(task_id)) {
            taskRepository.deleteById(task_id);
            return true;
        }
        return false;
    }

    public boolean isOwner(Long taskId, String currentUsername) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("Task doesn't found"));
        return task.getAuthorUsername().equals(currentUsername);
    }

}
