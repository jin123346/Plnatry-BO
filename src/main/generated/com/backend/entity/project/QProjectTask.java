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

    public static final QProjectTask projectTask = new QProjectTask("projectTask");

    public final NumberPath<Long> columnId = createNumber("columnId", Long.class);

    public final ListPath<ProjectComment, QProjectComment> comments = this.<ProjectComment, QProjectComment>createList("comments", ProjectComment.class, QProjectComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final DatePath<java.time.LocalDate> duedate = createDate("duedate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> priority = createNumber("priority", Integer.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final ListPath<ProjectSubTask, QProjectSubTask> subTasks = this.<ProjectSubTask, QProjectSubTask>createList("subTasks", ProjectSubTask.class, QProjectSubTask.class, PathInits.DIRECT2);

    public final ListPath<TaskTag, QTaskTag> tags = this.<TaskTag, QTaskTag>createList("tags", TaskTag.class, QTaskTag.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QProjectTask(String variable) {
        super(ProjectTask.class, forVariable(variable));
    }

    public QProjectTask(Path<? extends ProjectTask> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProjectTask(PathMetadata metadata) {
        super(ProjectTask.class, metadata);
    }

}

