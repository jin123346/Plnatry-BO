package com.backend.entity.project;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTaskTagId is a Querydsl query type for TaskTagId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QTaskTagId extends BeanPath<TaskTagId> {

    private static final long serialVersionUID = -137195849L;

    public static final QTaskTagId taskTagId = new QTaskTagId("taskTagId");

    public final NumberPath<Long> tagId = createNumber("tagId", Long.class);

    public final NumberPath<Long> taskId = createNumber("taskId", Long.class);

    public QTaskTagId(String variable) {
        super(TaskTagId.class, forVariable(variable));
    }

    public QTaskTagId(Path<? extends TaskTagId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTaskTagId(PathMetadata metadata) {
        super(TaskTagId.class, metadata);
    }

}

