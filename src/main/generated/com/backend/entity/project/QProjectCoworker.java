package com.backend.entity.project;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectCoworker is a Querydsl query type for ProjectCoworker
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectCoworker extends EntityPathBase<ProjectCoworker> {

    private static final long serialVersionUID = -1450206518L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectCoworker projectCoworker = new QProjectCoworker("projectCoworker");

    public final BooleanPath canAddTask = createBoolean("canAddTask");

    public final BooleanPath canDeleteTask = createBoolean("canDeleteTask");

    public final BooleanPath canEditProject = createBoolean("canEditProject");

    public final BooleanPath canRead = createBoolean("canRead");

    public final BooleanPath canUpdateTask = createBoolean("canUpdateTask");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isOwner = createBoolean("isOwner");

    public final QProject project;

    public final com.backend.entity.user.QUser user;

    public QProjectCoworker(String variable) {
        this(ProjectCoworker.class, forVariable(variable), INITS);
    }

    public QProjectCoworker(Path<? extends ProjectCoworker> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectCoworker(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectCoworker(PathMetadata metadata, PathInits inits) {
        this(ProjectCoworker.class, metadata, inits);
    }

    public QProjectCoworker(Class<? extends ProjectCoworker> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project")) : null;
        this.user = inits.isInitialized("user") ? new com.backend.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

