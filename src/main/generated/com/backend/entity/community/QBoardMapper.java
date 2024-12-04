package com.backend.entity.community;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardMapper is a Querydsl query type for BoardMapper
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardMapper extends EntityPathBase<BoardMapper> {

    private static final long serialVersionUID = -1421644866L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardMapper boardMapper = new QBoardMapper("boardMapper");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final QBoard board;

    public final NumberPath<Integer> canDelete = createNumber("canDelete", Integer.class);

    public final NumberPath<Integer> canRead = createNumber("canRead", Integer.class);

    public final NumberPath<Integer> canWrite = createNumber("canWrite", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.backend.entity.user.QUser user;

    public QBoardMapper(String variable) {
        this(BoardMapper.class, forVariable(variable), INITS);
    }

    public QBoardMapper(Path<? extends BoardMapper> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardMapper(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardMapper(PathMetadata metadata, PathInits inits) {
        this(BoardMapper.class, metadata, inits);
    }

    public QBoardMapper(Class<? extends BoardMapper> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
        this.user = inits.isInitialized("user") ? new com.backend.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

