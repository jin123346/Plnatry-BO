package com.backend.entity.page;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPermissionPage is a Querydsl query type for PermissionPage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPermissionPage extends EntityPathBase<PermissionPage> {

    private static final long serialVersionUID = 760292089L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPermissionPage permissionPage = new QPermissionPage("permissionPage");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final QPage page;

    public final EnumPath<PermissionPage.Role> role = createEnum("role", PermissionPage.Role.class);

    public final com.backend.entity.user.QUser user;

    public QPermissionPage(String variable) {
        this(PermissionPage.class, forVariable(variable), INITS);
    }

    public QPermissionPage(Path<? extends PermissionPage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPermissionPage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPermissionPage(PathMetadata metadata, PathInits inits) {
        this(PermissionPage.class, metadata, inits);
    }

    public QPermissionPage(Class<? extends PermissionPage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.page = inits.isInitialized("page") ? new QPage(forProperty("page"), inits.get("page")) : null;
        this.user = inits.isInitialized("user") ? new com.backend.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

