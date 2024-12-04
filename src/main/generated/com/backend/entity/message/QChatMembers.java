package com.backend.entity.message;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatMembers is a Querydsl query type for ChatMembers
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatMembers extends EntityPathBase<ChatMembers> {

    private static final long serialVersionUID = -637846602L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatMembers chatMembers = new QChatMembers("chatMembers");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QChatRoom room;

    public final com.backend.entity.user.QUser user;

    public QChatMembers(String variable) {
        this(ChatMembers.class, forVariable(variable), INITS);
    }

    public QChatMembers(Path<? extends ChatMembers> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatMembers(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatMembers(PathMetadata metadata, PathInits inits) {
        this(ChatMembers.class, metadata, inits);
    }

    public QChatMembers(Class<? extends ChatMembers> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.room = inits.isInitialized("room") ? new QChatRoom(forProperty("room"), inits.get("room")) : null;
        this.user = inits.isInitialized("user") ? new com.backend.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

