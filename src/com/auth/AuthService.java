
package com.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthService {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Session> sessions = new HashMap<>();
    private Map<String, Integer> failedAttempts = new HashMap<>();
    private static final int MAX_ATTEMPTS = 3;

    // putting registered user in map
    public void registerUser(User user) {
        users.put(user.getUsername(), user);
    }

    public Session login(String username, String password) {
        User user = users.get(username);

        if (user == null) {
            System.out.println("User not found!");
            return null;
        }

        int attempts = failedAttempts.getOrDefault(username, 0);
        if (attempts >= MAX_ATTEMPTS) {
            System.out.println("Account locked due to too many failed attempts.");
            return null;
        }

        if (user.authenticate(username, password)) {
            String sessionId = UUID.randomUUID().toString();
            Session session = new Session(sessionId, user);
            sessions.put(sessionId, session);
            failedAttempts.put(username, 0); // reset attempts
            System.out.println("Login successful. Session created: " + sessionId);
            return session;
        } else {
            failedAttempts.put(username, attempts + 1);
            System.out.println("Login failed. Attempt " + (attempts + 1));
            return null;
        }
    }
}
