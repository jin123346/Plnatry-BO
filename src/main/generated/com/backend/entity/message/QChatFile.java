package com.backend.entity.message;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChatFile is a Querydsl query type for ChatFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatFile extends EntityPathBase<ChatFile> {

    private static final long serialVersionUID = 957927423L;

    public static final QChatFile chatFile = new QChatFile("chatFile");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QChatFile(String variable) {
        super(ChatFile.class, forVariable(variable));
    }

    public QChatFile(Path<? extends ChatFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatFile(PathMetadata metadata) {
        super(ChatFile.class, metadata);
    }

}

