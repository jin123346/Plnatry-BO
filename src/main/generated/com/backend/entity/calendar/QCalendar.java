package com.backend.entity.calendar;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCalendar is a Querydsl query type for Calendar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCalendar extends EntityPathBase<Calendar> {

    private static final long serialVersionUID = -899717206L;

    public static final QCalendar calendar = new QCalendar("calendar");

    public final ListPath<CalendarContent, QCalendarContent> calendarContents = this.<CalendarContent, QCalendarContent>createList("calendarContents", CalendarContent.class, QCalendarContent.class, PathInits.DIRECT2);

    public final NumberPath<Long> calendarId = createNumber("calendarId", Long.class);

    public final ListPath<CalendarMapper, QCalendarMapper> calendars = this.<CalendarMapper, QCalendarMapper>createList("calendars", CalendarMapper.class, QCalendarMapper.class, PathInits.DIRECT2);

    public final StringPath color = createString("color");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QCalendar(String variable) {
        super(Calendar.class, forVariable(variable));
    }

    public QCalendar(Path<? extends Calendar> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCalendar(PathMetadata metadata) {
        super(Calendar.class, metadata);
    }

}

