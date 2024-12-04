package com.backend.entity.calendar;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCalendarMapper is a Querydsl query type for CalendarMapper
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCalendarMapper extends EntityPathBase<CalendarMapper> {

    private static final long serialVersionUID = -1832244597L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCalendarMapper calendarMapper = new QCalendarMapper("calendarMapper");

    public final QCalendar calendar;

    public final NumberPath<Integer> canDelete = createNumber("canDelete", Integer.class);

    public final NumberPath<Integer> canRead = createNumber("canRead", Integer.class);

    public final NumberPath<Integer> canShare = createNumber("canShare", Integer.class);

    public final NumberPath<Integer> canWrite = createNumber("canWrite", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.backend.entity.user.QUser user;

    public QCalendarMapper(String variable) {
        this(CalendarMapper.class, forVariable(variable), INITS);
    }

    public QCalendarMapper(Path<? extends CalendarMapper> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCalendarMapper(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCalendarMapper(PathMetadata metadata, PathInits inits) {
        this(CalendarMapper.class, metadata, inits);
    }

    public QCalendarMapper(Class<? extends CalendarMapper> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.calendar = inits.isInitialized("calendar") ? new QCalendar(forProperty("calendar")) : null;
        this.user = inits.isInitialized("user") ? new com.backend.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

