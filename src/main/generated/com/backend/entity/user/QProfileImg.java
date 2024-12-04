package com.backend.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProfileImg is a Querydsl query type for ProfileImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProfileImg extends EntityPathBase<ProfileImg> {

    private static final long serialVersionUID = -897409511L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProfileImg profileImg = new QProfileImg("profileImg");

    public final StringPath message = createString("message");

    public final NumberPath<Long> profileImgId = createNumber("profileImgId", Long.class);

    public final StringPath rName = createString("rName");

    public final StringPath sName = createString("sName");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final QUser user;

    public QProfileImg(String variable) {
        this(ProfileImg.class, forVariable(variable), INITS);
    }

    public QProfileImg(Path<? extends ProfileImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProfileImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProfileImg(PathMetadata metadata, PathInits inits) {
        this(ProfileImg.class, metadata, inits);
    }

    public QProfileImg(Class<? extends ProfileImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

