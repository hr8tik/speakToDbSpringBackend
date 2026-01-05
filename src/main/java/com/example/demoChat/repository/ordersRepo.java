package com.example.demoChat.repository;

import com.example.demoChat.entitiy.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ordersRepo extends JpaRepository<Order,Integer> {
}
