package com.example.demoChat.service;

import org.springframework.stereotype.Component;

@Component
public class JPQLValidator {

    public void validate(String jpql) {
        String upper = jpql.toUpperCase();

        if (!upper.startsWith("SELECT")) {
            throw new RuntimeException("Only SELECT queries are allowed");
        }

        if (upper.contains("DELETE") ||
                upper.contains("UPDATE") ||
                upper.contains("INSERT") ||
                upper.contains("DROP")) {
            throw new RuntimeException("Dangerous query blocked");
        }
    }
}
