package com.hantel.tasktracker.taskservice.controller;

import com.hantel.tasktracker.taskservice.entity.Task;
import com.hantel.tasktracker.taskservice.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/getAll")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable UUID id) {
        Task task = taskRepository.findById(id).orElse(null);

        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable UUID id, @RequestBody Task task) {
        Task persistedTask = taskRepository.findById(id).orElse(null);

        if (persistedTask == null) {
            return ResponseEntity.notFound().build();
        }

        persistedTask.setTitle(task.getTitle());
        persistedTask.setDescription(task.getDescription());
        persistedTask.setStatus(task.getStatus());

        Task updatedTask = taskRepository.save(persistedTask);

        return ResponseEntity.ok(updatedTask);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        Task task = taskRepository.findById(id).orElse(null);

        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        taskRepository.delete(task);

        return ResponseEntity.noContent().build();
    }
}
