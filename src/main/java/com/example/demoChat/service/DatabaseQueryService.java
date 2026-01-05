package com.example.demoChat.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseQueryService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<?> execute(String jpql) {
        List<?> result = entityManager.createQuery(jpql).getResultList();
        System.out.println("RESULT SIZE = " + result.size());
        System.out.println("FIRST ITEM = " + result.get(0));
        return result;    }
}
