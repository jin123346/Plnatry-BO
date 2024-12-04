package com.backend.entity.page;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPage is a Querydsl query type for Page
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPage extends EntityPathBase<Page> {

    private static final long serialVersionUID = 680826442L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPage page = new QPage("page");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final BooleanPath isShared = createBoolean("isShared");

    public final QPage parent;

    public final StringPath title = createString("title");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.backend.entity.user.QUser user;

    public QPage(String variable) {
        this(Page.class, forVariable(variable), INITS);
    }

    public QPage(Path<? extends Page> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPage(PathMetadata metadata, PathInits inits) {
        this(Page.class, metadata, inits);
    }

    public QPage(Class<? extends Page> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QPage(forProperty("parent"), inits.get("parent")) : null;
        this.user = inits.isInitialized("user") ? new com.backend.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

