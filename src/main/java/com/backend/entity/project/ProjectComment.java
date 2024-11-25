package com.backend.entity.project;

import com.backend.entity.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

public class ProjectComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_id")
    private Long taskId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String content;

    @CreationTimestamp
    private LocalDateTime rdate;
}