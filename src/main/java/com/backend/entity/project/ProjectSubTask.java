package com.backend.entity.project;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ProjectSubTask { //Task 내부 체크리스트
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_id")
    private Long taskId;

    private boolean isChecked; // 리스트 체크 여부
    private String subTaskName; // 이름
}