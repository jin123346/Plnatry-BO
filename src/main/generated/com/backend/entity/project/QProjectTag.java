package com.backend.entity.project;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectTag is a Querydsl query type for ProjectTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectTag extends EntityPathBase<ProjectTag> {

    private static final long serialVersionUID = 1386125242L;

    public static final QProjectTag projectTag = new QProjectTag("projectTag");

    public final StringPath color = createString("color");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<TaskTag, QTaskTag> taskTags = this.<TaskTag, QTaskTag>createList("taskTags", TaskTag.class, QTaskTag.class, PathInits.DIRECT2);

    public QProjectTag(String variable) {
        super(ProjectTag.class, forVariable(variable));
    }

    public QProjectTag(Path<? extends ProjectTag> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProjectTag(PathMetadata metadata) {
        super(ProjectTag.class, metadata);
    }

}

