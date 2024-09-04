package com.example.tripplanningservice.repository;

import com.example.tripplanningservice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findById(long id);
}
