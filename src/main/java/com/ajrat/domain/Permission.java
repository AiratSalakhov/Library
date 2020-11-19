package com.ajrat.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Permission {
    READERS_READ("readers:read"),
    READERS_WRITE("readers:write");

    private final String permission;
}