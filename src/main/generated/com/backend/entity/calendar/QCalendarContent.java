package com.backend.entity.calendar;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCalendarContent is a Querydsl query type for CalendarContent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCalendarContent extends EntityPathBase<CalendarContent> {

    private static final long serialVersionUID = -851029649L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCalendarContent calendarContent = new QCalendarContent("calendarContent");

    public final NumberPath<Integer> alertStatus = createNumber("alertStatus", Integer.class);

    public final QCalendar calendar;

    public final NumberPath<Long> calendarContentId = createNumber("calendarContentId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> calendarEndDate = createDateTime("calendarEndDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> calendarStartDate = createDateTime("calendarStartDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> importance = createNumber("importance", Integer.class);

    public final StringPath location = createString("location");

    public final StringPath memo = createString("memo");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QCalendarContent(String variable) {
        this(CalendarContent.class, forVariable(variable), INITS);
    }

    public QCalendarContent(Path<? extends CalendarContent> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCalendarContent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCalendarContent(PathMetadata metadata, PathInits inits) {
        this(CalendarContent.class, metadata, inits);
    }

    public QCalendarContent(Class<? extends CalendarContent> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.calendar = inits.isInitialized("calendar") ? new QCalendar(forProperty("calendar")) : null;
    }

}

