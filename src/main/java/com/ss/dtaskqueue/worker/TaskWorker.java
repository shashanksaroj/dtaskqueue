package com.ss.dtaskqueue.worker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.ss.dtaskqueue.entity.Task;
import com.ss.dtaskqueue.service.TaskService;

@Component
public class TaskWorker {

    private final TaskService service;

    public TaskWorker(TaskService service) {
        this.service = service;
    }

    @RabbitListener(queues = "task_queue")
    public void handleTask(Task task) {
        service.processTask(task);
        System.out.println("Processed task: " + task.getId() + " | Status: " + task.getStatus());
    }
}
