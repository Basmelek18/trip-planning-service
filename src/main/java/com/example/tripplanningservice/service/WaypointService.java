package com.example.tripplanningservice.service;

import com.example.tripplanningservice.dto.*;
import com.example.tripplanningservice.exception.NotFoundException;
import com.example.tripplanningservice.model.Route;
import com.example.tripplanningservice.model.Waypoint;
import com.example.tripplanningservice.repository.RouteRepository;
import com.example.tripplanningservice.repository.WaypointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WaypointService {
    private final WaypointRepository waypointRepository;
    private final RouteRepository routeRepository;

    @Transactional
    public FullWaypointDTO getWaypoint(long routeId, long waypointId) {
        Waypoint waypoint = waypointRepository.findById(waypointId);
        if (waypoint == null) {
            throw new NotFoundException("Waypoint doesn't found");
        }
        if (routeId != waypoint.getRoute().getId()) {
            throw new NotFoundException("Waypoint doesn't found");
        }
        return WaypointMapper.toFullWaypointDTO(waypoint);
    }

    @Transactional
    public WaypointDTO createWaypoint(String username, WaypointDTO waypointDTO, long routeId) {
        Route route = routeRepository.findById(routeId);
        if (route == null) {
            throw new NotFoundException("Route doesn't found");
        }
        Waypoint waypoint = new Waypoint();
        waypoint.setLocationName(waypointDTO.getLocationName());
        waypoint.setAuthorUsername(username);
        waypoint.setLatitude(waypointDTO.getLatitude());
        waypoint.setLongitude(waypointDTO.getLongitude());
        waypoint.setRoute(route);
        waypointRepository.save(waypoint);
        return WaypointMapper.toWaypointDTO(waypoint);
    }

    @Transactional
    public WaypointDTO updateWaypoint(WaypointDTO waypointDTO, long routeId, long waypointId) {
        Waypoint waypoint = waypointRepository.findById(waypointId);
        if (waypoint == null) {
            throw new NotFoundException("Waypoint doesn't found");
        }
        if (routeId != waypoint.getRoute().getId()) {
            throw new NotFoundException("Waypoint doesn't found");
        }
        waypoint.setLocationName(waypointDTO.getLocationName());
        waypoint.setLatitude(waypointDTO.getLatitude());
        waypoint.setLongitude(waypointDTO.getLongitude());
        waypointRepository.save(waypoint);
        return WaypointMapper.toWaypointDTO(waypoint);
    }

    @Transactional
    public boolean deleteWaypoint(long waypointId) {
        if (waypointRepository.existsById(waypointId)) {
            waypointRepository.deleteById(waypointId);
            return true;
        }
        return false;
    }

    public boolean isOwner(Long waypointId, String currentUsername) {
        Waypoint waypoint = waypointRepository.findById(waypointId)
                .orElseThrow(() -> new NotFoundException("Waypoint doesn't found"));
        return waypoint.getAuthorUsername().equals(currentUsername);
    }
}
