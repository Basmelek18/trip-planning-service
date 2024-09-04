package com.example.tripplanningservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "route")
@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "start_location", nullable = false)
    private String startLocation;
    @Column(name = "end_location", nullable = false)
    private String endLocation;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Waypoint> waypoints;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Task> tasks;
}
