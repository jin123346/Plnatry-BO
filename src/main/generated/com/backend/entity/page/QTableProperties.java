package com.backend.entity.page;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTableProperties is a Querydsl query type for TableProperties
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTableProperties extends EntityPathBase<TableProperties> {

    private static final long serialVersionUID = -1245743866L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTableProperties tableProperties = new QTableProperties("tableProperties");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> order = createNumber("order", Integer.class);

    public final QBlock table;

    public final EnumPath<TableProperties.ColumnType> type = createEnum("type", TableProperties.ColumnType.class);

    public QTableProperties(String variable) {
        this(TableProperties.class, forVariable(variable), INITS);
    }

    public QTableProperties(Path<? extends TableProperties> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTableProperties(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTableProperties(PathMetadata metadata, PathInits inits) {
        this(TableProperties.class, metadata, inits);
    }

    public QTableProperties(Class<? extends TableProperties> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.table = inits.isInitialized("table") ? new QBlock(forProperty("table"), inits.get("table")) : null;
    }

}

