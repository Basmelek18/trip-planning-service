package com.example.tripplanningservice.service;

import com.example.tripplanningservice.dto.WaypointDTO;
import com.example.tripplanningservice.dto.WaypointMapper;
import com.example.tripplanningservice.exception.RouteNotFoundException;
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
    public WaypointDTO createWaypoint(WaypointDTO waypointDTO, long id) {
        Route route = routeRepository.findById(id);
        if (route == null) {
            throw new RouteNotFoundException("Route doesn't found");
        }
        Waypoint waypoint = new Waypoint();
        waypoint.setLocationName(waypointDTO.getLocationName());
        waypoint.setLatitude(waypointDTO.getLatitude());
        waypoint.setLongitude(waypointDTO.getLongitude());
        waypoint.setRoute(route);
        waypointRepository.save(waypoint);
        return WaypointMapper.toWaypointDTO(waypoint);
    }
}
