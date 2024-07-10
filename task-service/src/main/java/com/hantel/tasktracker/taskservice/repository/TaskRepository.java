package com.hantel.tasktracker.taskservice.repository;

import com.hantel.tasktracker.taskservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface TaskRepository extends JpaRepository<Task, UUID> {
}
