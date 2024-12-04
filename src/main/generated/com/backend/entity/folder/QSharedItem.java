package com.backend.entity.folder;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSharedItem is a Querydsl query type for SharedItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSharedItem extends EntityPathBase<SharedItem> {

    private static final long serialVersionUID = 910072404L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSharedItem sharedItem = new QSharedItem("sharedItem");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> itemId = createNumber("itemId", Long.class);

    public final EnumPath<SharedItem.ItemType> itemType = createEnum("itemType", SharedItem.ItemType.class);

    public final NumberPath<Integer> permissions = createNumber("permissions", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> sharedAt = createDateTime("sharedAt", java.time.LocalDateTime.class);

    public final com.backend.entity.user.QUser sharedBy;

    public final com.backend.entity.user.QUser sharedWith;

    public QSharedItem(String variable) {
        this(SharedItem.class, forVariable(variable), INITS);
    }

    public QSharedItem(Path<? extends SharedItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSharedItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSharedItem(PathMetadata metadata, PathInits inits) {
        this(SharedItem.class, metadata, inits);
    }

    public QSharedItem(Class<? extends SharedItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sharedBy = inits.isInitialized("sharedBy") ? new com.backend.entity.user.QUser(forProperty("sharedBy"), inits.get("sharedBy")) : null;
        this.sharedWith = inits.isInitialized("sharedWith") ? new com.backend.entity.user.QUser(forProperty("sharedWith"), inits.get("sharedWith")) : null;
    }

}

