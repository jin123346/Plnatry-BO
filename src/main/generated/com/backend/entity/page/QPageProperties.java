package com.backend.entity.page;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPageProperties is a Querydsl query type for PageProperties
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPageProperties extends EntityPathBase<PageProperties> {

    private static final long serialVersionUID = -905172195L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPageProperties pageProperties = new QPageProperties("pageProperties");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath name = createString("name");

    public final QPage page;

    public final EnumPath<PageProperties.PropertyType> type = createEnum("type", PageProperties.PropertyType.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final StringPath value = createString("value");

    public QPageProperties(String variable) {
        this(PageProperties.class, forVariable(variable), INITS);
    }

    public QPageProperties(Path<? extends PageProperties> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPageProperties(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPageProperties(PathMetadata metadata, PathInits inits) {
        this(PageProperties.class, metadata, inits);
    }

    public QPageProperties(Class<? extends PageProperties> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.page = inits.isInitialized("page") ? new QPage(forProperty("page"), inits.get("page")) : null;
    }

}

