package com.backend.entity.project;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProject is a Querydsl query type for Project
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProject extends EntityPathBase<Project> {

    private static final long serialVersionUID = -1516333120L;

    public static final QProject project = new QProject("project");

    public final ListPath<ProjectColumn, QProjectColumn> columns = this.<ProjectColumn, QProjectColumn>createList("columns", ProjectColumn.class, QProjectColumn.class, PathInits.DIRECT2);

    public final ListPath<ProjectCoworker, QProjectCoworker> coworkers = this.<ProjectCoworker, QProjectCoworker>createList("coworkers", ProjectCoworker.class, QProjectCoworker.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> projectProgress = createNumber("projectProgress", Integer.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QProject(String variable) {
        super(Project.class, forVariable(variable));
    }

    public QProject(Path<? extends Project> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProject(PathMetadata metadata) {
        super(Project.class, metadata);
    }

}

