package com.backend.entity.group;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGroupMapper is a Querydsl query type for GroupMapper
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupMapper extends EntityPathBase<GroupMapper> {

    private static final long serialVersionUID = 624123949L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGroupMapper groupMapper = new QGroupMapper("groupMapper");

    public final QGroup group;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.backend.entity.user.QUser user;

    public QGroupMapper(String variable) {
        this(GroupMapper.class, forVariable(variable), INITS);
    }

    public QGroupMapper(Path<? extends GroupMapper> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGroupMapper(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGroupMapper(PathMetadata metadata, PathInits inits) {
        this(GroupMapper.class, metadata, inits);
    }

    public QGroupMapper(Class<? extends GroupMapper> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.group = inits.isInitialized("group") ? new QGroup(forProperty("group"), inits.get("group")) : null;
        this.user = inits.isInitialized("user") ? new com.backend.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

