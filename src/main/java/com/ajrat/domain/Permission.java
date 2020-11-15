package com.ajrat.domain;

public enum Permission {
    READERS_READ("readers:read"),
    READERS_WRITE("readers:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}