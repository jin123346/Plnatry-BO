package com.backend.entity.project;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Entity
@Table(name = "project_column")
public class ProjectColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String color;

    @ManyToOne
    private Project project;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "columnId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectTask> tasks;

}