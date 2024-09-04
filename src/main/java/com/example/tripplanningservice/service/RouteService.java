package com.example.tripplanningservice.service;

import com.example.tripplanningservice.dto.RouteDTO;
import com.example.tripplanningservice.dto.RouteMapper;
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
    public RouteDTO createRoute(RouteDTO routeDTO) {
        Route route = new Route();
        route.setName(routeDTO.getName());
        route.setStartLocation(routeDTO.getStartLocation());
        route.setEndLocation(routeDTO.getEndLocation());
        routeRepository.save(route);
        return RouteMapper.toRouteDTO(route);
    }

    @Transactional
    public RouteDTO updateRoute(RouteDTO routeDTO, long id) {
        Route route = routeRepository.findById(id);
        route.setName(routeDTO.getName());
        route.setStartLocation(routeDTO.getStartLocation());
        route.setStartLocation(routeDTO.getEndLocation());
        routeRepository.save(route);
        return RouteMapper.toRouteDTO(route);
    }
}
