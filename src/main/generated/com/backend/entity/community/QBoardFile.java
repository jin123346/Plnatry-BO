package com.backend.entity.community;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardFile is a Querydsl query type for BoardFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardFile extends EntityPathBase<BoardFile> {

    private static final long serialVersionUID = -1543578055L;

    public static final QBoardFile boardFile = new QBoardFile("boardFile");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final NumberPath<Integer> articleNo = createNumber("articleNo", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> downloadCount = createNumber("downloadCount", Integer.class);

    public final NumberPath<Integer> fileNo = createNumber("fileNo", Integer.class);

    public final StringPath oName = createString("oName");

    public final StringPath sName = createString("sName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QBoardFile(String variable) {
        super(BoardFile.class, forVariable(variable));
    }

    public QBoardFile(Path<? extends BoardFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardFile(PathMetadata metadata) {
        super(BoardFile.class, metadata);
    }

}

