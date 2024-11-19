package com.example.tripplanningservice.service;

import com.example.tripplanningservice.dto.FullRouteDTO;
import com.example.tripplanningservice.dto.RouteDTO;
import com.example.tripplanningservice.dto.RouteMapper;
import com.example.tripplanningservice.exception.NotFoundException;
import com.example.tripplanningservice.model.Route;
import com.example.tripplanningservice.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RouteService {
    private final RouteRepository routeRepository;

    @Transactional
    public FullRouteDTO getRoute(long id) {
        Route route = routeRepository.findById(id);
        if (route == null) {
            throw new NotFoundException("Route doesn't exist");
        }
        return RouteMapper.toFullRouteDTO(route);
    }

    @Transactional
    public List<FullRouteDTO> getAllRoutes() {
        return routeRepository.findAll()
                .stream()
                .map(RouteMapper::toFullRouteDTO)
                .collect(Collectors.toList());
    }

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
            throw new NotFoundException("Route doesn't exist");
        }
        route.setName(routeDTO.getName());
        route.setStartLocation(routeDTO.getStartLocation());
        route.setEndLocation(routeDTO.getEndLocation());
        routeRepository.save(route);
        return RouteMapper.toRouteDTO(route);
    }

    @Transactional
    public boolean deleteRoute(long id) {
        if (routeRepository.existsById(id)) {
            routeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean isOwner(Long routeId, String currentUsername) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new NotFoundException("Route not found"));
        return route.getAuthorUsername().equals(currentUsername);
    }

}
