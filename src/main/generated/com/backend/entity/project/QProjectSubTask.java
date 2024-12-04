package com.backend.entity.project;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProjectSubTask is a Querydsl query type for ProjectSubTask
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectSubTask extends EntityPathBase<ProjectSubTask> {

    private static final long serialVersionUID = 450105349L;

    public static final QProjectSubTask projectSubTask = new QProjectSubTask("projectSubTask");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isChecked = createBoolean("isChecked");

    public final StringPath name = createString("name");

    public final NumberPath<Long> taskId = createNumber("taskId", Long.class);

    public QProjectSubTask(String variable) {
        super(ProjectSubTask.class, forVariable(variable));
    }

    public QProjectSubTask(Path<? extends ProjectSubTask> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProjectSubTask(PathMetadata metadata) {
        super(ProjectSubTask.class, metadata);
    }

}

