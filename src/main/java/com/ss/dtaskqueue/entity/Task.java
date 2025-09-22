package com.ss.dtaskqueue.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Task {

    @Id
    private String id;

    @Column(nullable = false)
    private String payload;

    @Column(nullable = false)
    private String status; // PENDING, PROCESSING, COMPLETED, FAILED

    private int attempts = 0;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}
