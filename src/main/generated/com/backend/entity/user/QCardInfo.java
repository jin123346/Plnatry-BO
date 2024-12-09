package com.backend.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCardInfo is a Querydsl query type for CardInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCardInfo extends EntityPathBase<CardInfo> {

    private static final long serialVersionUID = 1104495709L;

    public static final QCardInfo cardInfo = new QCardInfo("cardInfo");

    public final NumberPath<Integer> activeStatus = createNumber("activeStatus", Integer.class);

    public final NumberPath<Integer> autoPayment = createNumber("autoPayment", Integer.class);

    public final NumberPath<Long> cardId = createNumber("cardId", Long.class);

    public final StringPath paymentCardCvc = createString("paymentCardCvc");

    public final StringPath paymentCardExpiration = createString("paymentCardExpiration");

    public final StringPath paymentCardNick = createString("paymentCardNick");

    public final StringPath paymentCardNo = createString("paymentCardNo");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QCardInfo(String variable) {
        super(CardInfo.class, forVariable(variable));
    }

    public QCardInfo(Path<? extends CardInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCardInfo(PathMetadata metadata) {
        super(CardInfo.class, metadata);
    }

}

