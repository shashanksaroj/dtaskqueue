package com.ss.dtaskqueue.controller;


import org.springframework.web.bind.annotation.*;

import com.ss.dtaskqueue.entity.Task;
import com.ss.dtaskqueue.service.TaskService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public Task enqueueTask(@RequestBody Map<String, String> payload) {
        return service.enqueueTask(payload.get("payload"));
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }
}
