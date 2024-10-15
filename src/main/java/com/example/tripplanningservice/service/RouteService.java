package com.example.tripplanningservice.service;

import com.example.tripplanningservice.dto.RouteDTO;
import com.example.tripplanningservice.dto.RouteMapper;
import com.example.tripplanningservice.exception.NotFoundException;
import com.example.tripplanningservice.exception.RouteNotFoundException;
import com.example.tripplanningservice.model.Route;
import com.example.tripplanningservice.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RouteService {
    private final RouteRepository routeRepository;

    @Transactional
    public RouteDTO createRoute(String username, RouteDTO routeDTO) {
        Route route = new Route();
        route.setName(routeDTO.getName());
        route.setAuthorUsername(username);
        route.setStartLocation(routeDTO.getStartLocation());
        route.setEndLocation(routeDTO.getEndLocation());
        routeRepository.save(route);
        return RouteMapper.toRouteDTO(route);
    }

    @Transactional
    public RouteDTO updateRoute(RouteDTO routeDTO, long id) {
        Route route = routeRepository.findById(id);
        if (route == null) {
            throw new RouteNotFoundException("Route doesn't exist");
        }
        route.setName(routeDTO.getName());
        route.setStartLocation(routeDTO.getStartLocation());
        route.setStartLocation(routeDTO.getEndLocation());
        routeRepository.save(route);
        return RouteMapper.toRouteDTO(route);
    }

    public boolean isOwner(Long routeId, String currentUsername) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new NotFoundException("Route not found"));
        return route.getAuthorUsername().equals(currentUsername);
    }

}
