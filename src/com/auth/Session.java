package com.auth;

import java.time.LocalDateTime;

public class Session {
    private String sessionId;
    private User user;
    private LocalDateTime createdAt;
    private static final int TIMEOUT_MINUTES = 30;

    public Session(String sessionId, User user) {
        this.sessionId = sessionId;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }

    public boolean isActive() {
        return LocalDateTime.now().isBefore(createdAt.plusMinutes(TIMEOUT_MINUTES));
    }

    public User getUser() {
        return user;
    }

    public String getSessionId() {
        return sessionId;
    }
}
