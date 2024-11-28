package com.backend.util;

public enum PermissionType {
    READ(1),
    WRITE(2),
    FULL(4),
    SHARE(8);
    private final int value;

    PermissionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
