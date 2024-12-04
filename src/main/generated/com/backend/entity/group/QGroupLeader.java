package com.backend.entity.group;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGroupLeader is a Querydsl query type for GroupLeader
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupLeader extends EntityPathBase<GroupLeader> {

    private static final long serialVersionUID = 598730485L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGroupLeader groupLeader = new QGroupLeader("groupLeader");

    public final QGroup group;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.backend.entity.user.QUser user;

    public QGroupLeader(String variable) {
        this(GroupLeader.class, forVariable(variable), INITS);
    }

    public QGroupLeader(Path<? extends GroupLeader> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGroupLeader(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGroupLeader(PathMetadata metadata, PathInits inits) {
        this(GroupLeader.class, metadata, inits);
    }

    public QGroupLeader(Class<? extends GroupLeader> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.group = inits.isInitialized("group") ? new QGroup(forProperty("group"), inits.get("group")) : null;
        this.user = inits.isInitialized("user") ? new com.backend.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

