package com.backend.entity.folder;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDragHistory is a Querydsl query type for DragHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDragHistory extends EntityPathBase<DragHistory> {

    private static final long serialVersionUID = -115131644L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDragHistory dragHistory = new QDragHistory("dragHistory");

    public final DateTimePath<java.time.LocalDateTime> dragAt = createDateTime("dragAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> itemId = createNumber("itemId", Long.class);

    public final StringPath itemType = createString("itemType");

    public final StringPath newParent = createString("newParent");

    public final StringPath oldParent = createString("oldParent");

    public final com.backend.entity.user.QUser user;

    public QDragHistory(String variable) {
        this(DragHistory.class, forVariable(variable), INITS);
    }

    public QDragHistory(Path<? extends DragHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDragHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDragHistory(PathMetadata metadata, PathInits inits) {
        this(DragHistory.class, metadata, inits);
    }

    public QDragHistory(Class<? extends DragHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.backend.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

