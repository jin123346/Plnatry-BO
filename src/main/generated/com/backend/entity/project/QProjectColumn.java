package com.backend.entity.project;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectColumn is a Querydsl query type for ProjectColumn
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectColumn extends EntityPathBase<ProjectColumn> {

    private static final long serialVersionUID = 1767999286L;

    public static final QProjectColumn projectColumn = new QProjectColumn("projectColumn");

    public final StringPath color = createString("color");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<ProjectTask, QProjectTask> tasks = this.<ProjectTask, QProjectTask>createList("tasks", ProjectTask.class, QProjectTask.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QProjectColumn(String variable) {
        super(ProjectColumn.class, forVariable(variable));
    }

    public QProjectColumn(Path<? extends ProjectColumn> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProjectColumn(PathMetadata metadata) {
        super(ProjectColumn.class, metadata);
    }

}

