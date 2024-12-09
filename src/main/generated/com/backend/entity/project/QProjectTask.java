package com.backend.entity.project;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectTask is a Querydsl query type for ProjectTask
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectTask extends EntityPathBase<ProjectTask> {

    private static final long serialVersionUID = 20210021L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectTask projectTask = new QProjectTask("projectTask");

    public final QProjectColumn column;

    public final ListPath<ProjectComment, QProjectComment> comments = this.<ProjectComment, QProjectComment>createList("comments", ProjectComment.class, QProjectComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final DatePath<java.time.LocalDate> duedate = createDate("duedate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> position = createNumber("position", Integer.class);

    public final NumberPath<Integer> priority = createNumber("priority", Integer.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final ListPath<ProjectSubTask, QProjectSubTask> subTasks = this.<ProjectSubTask, QProjectSubTask>createList("subTasks", ProjectSubTask.class, QProjectSubTask.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QProjectTask(String variable) {
        this(ProjectTask.class, forVariable(variable), INITS);
    }

    public QProjectTask(Path<? extends ProjectTask> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectTask(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectTask(PathMetadata metadata, PathInits inits) {
        this(ProjectTask.class, metadata, inits);
    }

    public QProjectTask(Class<? extends ProjectTask> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.column = inits.isInitialized("column") ? new QProjectColumn(forProperty("column"), inits.get("column")) : null;
    }

}

