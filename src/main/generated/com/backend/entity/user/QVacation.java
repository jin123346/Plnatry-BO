package com.backend.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVacation is a Querydsl query type for Vacation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVacation extends EntityPathBase<Vacation> {

    private static final long serialVersionUID = -572777636L;

    public static final QVacation vacation = new QVacation("vacation");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QVacation(String variable) {
        super(Vacation.class, forVariable(variable));
    }

    public QVacation(Path<? extends Vacation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVacation(PathMetadata metadata) {
        super(Vacation.class, metadata);
    }

}

