package com.backend.entity.message;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChatFileImg is a Querydsl query type for ChatFileImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatFileImg extends EntityPathBase<ChatFileImg> {

    private static final long serialVersionUID = 1853217604L;

    public static final QChatFileImg chatFileImg = new QChatFileImg("chatFileImg");

    public final QChatFile _super = new QChatFile(this);

    public final StringPath chatFileRname = createString("chatFileRname");

    public final StringPath chatFileSname = createString("chatFileSname");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final NumberPath<Integer> status = _super.status;

    public QChatFileImg(String variable) {
        super(ChatFileImg.class, forVariable(variable));
    }

    public QChatFileImg(Path<? extends ChatFileImg> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatFileImg(PathMetadata metadata) {
        super(ChatFileImg.class, metadata);
    }

}

