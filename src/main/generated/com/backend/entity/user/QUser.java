package com.backend.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 136641482L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final StringPath addr1 = createString("addr1");

    public final StringPath addr2 = createString("addr2");

    public final ListPath<Attendance, QAttendance> attendance = this.<Attendance, QAttendance>createList("attendance", Attendance.class, QAttendance.class, PathInits.DIRECT2);

    public final ListPath<com.backend.entity.calendar.CalendarMapper, com.backend.entity.calendar.QCalendarMapper> calendars = this.<com.backend.entity.calendar.CalendarMapper, com.backend.entity.calendar.QCalendarMapper>createList("calendars", com.backend.entity.calendar.CalendarMapper.class, com.backend.entity.calendar.QCalendarMapper.class, PathInits.DIRECT2);

    public final StringPath company = createString("company");

    public final StringPath companyCode = createString("companyCode");

    public final StringPath country = createString("country");

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final StringPath day = createString("day");

    public final StringPath email = createString("email");

    public final ListPath<com.backend.entity.community.FavoriteBoard, com.backend.entity.community.QFavoriteBoard> favoriteBoards = this.<com.backend.entity.community.FavoriteBoard, com.backend.entity.community.QFavoriteBoard>createList("favoriteBoards", com.backend.entity.community.FavoriteBoard.class, com.backend.entity.community.QFavoriteBoard.class, PathInits.DIRECT2);

    public final NumberPath<Integer> grade = createNumber("grade", Integer.class);

    public final ListPath<com.backend.entity.group.GroupMapper, com.backend.entity.group.QGroupMapper> groupMappers = this.<com.backend.entity.group.GroupMapper, com.backend.entity.group.QGroupMapper>createList("groupMappers", com.backend.entity.group.GroupMapper.class, com.backend.entity.group.QGroupMapper.class, PathInits.DIRECT2);

    public final StringPath hp = createString("hp");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> joinDate = createDate("joinDate", java.time.LocalDate.class);

    public final DateTimePath<java.time.LocalDateTime> lastLogin = createDateTime("lastLogin", java.time.LocalDateTime.class);

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> paymentId = createNumber("paymentId", Long.class);

    public final QProfileImg profileImg;

    public final StringPath pwd = createString("pwd");

    public final EnumPath<com.backend.util.Role> role = createEnum("role", com.backend.util.Role.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath uid = createString("uid");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profileImg = inits.isInitialized("profileImg") ? new QProfileImg(forProperty("profileImg"), inits.get("profileImg")) : null;
    }

}

