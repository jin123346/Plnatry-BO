package com.backend.entity.project;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class ProjectTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "column_id")
    private Long columnId;

    private String title; // 할일
    private String content; // 세부사항
    private int priority; // 중요도

    private int status; // 완료, 미완료

    private LocalDate dueDate; // 마감일
    private int position; // 보드 내 위치

    @OneToMany
    private List<ProjectSubTask> subTasks;

    @OneToMany
    private List<ProjectComment> comments;
}
