package com.ss.dtaskqueue.service;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.ss.dtaskqueue.entity.Task;
import com.ss.dtaskqueue.repository.TaskRepository;

import static com.ss.dtaskqueue.config.RabbitConfig.TASK_QUEUE;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;




@Service
public class TaskService {

    private final RabbitTemplate rabbitTemplate;
    private final TaskRepository repository;
    private final int MAX_RETRIES = 3;

    public TaskService(RabbitTemplate rabbitTemplate, TaskRepository repository) {
        this.rabbitTemplate = rabbitTemplate;
        this.repository = repository;
    }

    public Task enqueueTask(String payload) {
        Task task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setPayload(payload);
        task.setStatus("PENDING");
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        repository.save(task);

        rabbitTemplate.convertAndSend(TASK_QUEUE, task);
        return task;
    }

    public void processTask(Task task) {
        task.setStatus("PROCESSING");
        task.setUpdatedAt(LocalDateTime.now());
        repository.save(task);

        try {
            // Simulate processing
            Thread.sleep(500);
            // Mark completed
            task.setStatus("COMPLETED");
        } catch (Exception e) {
            task.setAttempts(task.getAttempts() + 1);
            if (task.getAttempts() >= MAX_RETRIES) {
                task.setStatus("FAILED");
            } else {
                task.setStatus("PENDING");
                rabbitTemplate.convertAndSend(TASK_QUEUE, task);
            }
        } finally {
            task.setUpdatedAt(LocalDateTime.now());
            repository.save(task);
        }
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }
}
