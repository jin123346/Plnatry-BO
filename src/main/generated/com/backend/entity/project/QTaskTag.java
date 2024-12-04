package com.backend.entity.project;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTaskTag is a Querydsl query type for TaskTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTaskTag extends EntityPathBase<TaskTag> {

    private static final long serialVersionUID = 1550693500L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaskTag taskTag = new QTaskTag("taskTag");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final QTaskTagId id;

    public final QProjectTag tag;

    public final QProjectTask task;

    public QTaskTag(String variable) {
        this(TaskTag.class, forVariable(variable), INITS);
    }

    public QTaskTag(Path<? extends TaskTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTaskTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTaskTag(PathMetadata metadata, PathInits inits) {
        this(TaskTag.class, metadata, inits);
    }

    public QTaskTag(Class<? extends TaskTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QTaskTagId(forProperty("id")) : null;
        this.tag = inits.isInitialized("tag") ? new QProjectTag(forProperty("tag")) : null;
        this.task = inits.isInitialized("task") ? new QProjectTask(forProperty("task")) : null;
    }

}

