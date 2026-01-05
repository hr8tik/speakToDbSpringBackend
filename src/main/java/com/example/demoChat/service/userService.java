package com.example.demoChat.service;

import com.example.demoChat.entitiy.Order;
import com.example.demoChat.entitiy.UserEntity;
import com.example.demoChat.repository.ordersRepo;
import com.example.demoChat.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {

    @Autowired
    private userRepo repo;
    @Autowired
    private ordersRepo orderrepo;

    public List<UserEntity> getAll() {
        return  repo.findAll();
    }

    public UserEntity postData(UserEntity user) {
        return repo.save(user);
    }

    public List<Order> getAllOrders() {
        return orderrepo.findAll();
    }
}
