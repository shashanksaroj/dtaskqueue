package com.ss.dtaskqueue.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ss.dtaskqueue.entity.Task;

public interface TaskRepository extends JpaRepository<Task, String> {
}
