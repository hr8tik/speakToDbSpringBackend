package com.example.demoChat.service;

import com.example.demoChat.entitiy.Order;
import com.example.demoChat.entitiy.UserEntity;
import com.example.demoChat.repository.ordersRepo;
import com.example.demoChat.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public UserEntity updateUser(UserEntity user) {

        if (user.getId() == null) {
            throw new RuntimeException("User ID must be provided for update");
        }

        UserEntity existingUser = repo.findById(user.getId())
                .orElseThrow(() -> new RuntimeException(
                        "User not found with id: " + user.getId()));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setCity(user.getCity());
        existingUser.setMobileNumber(user.getMobileNumber());
        existingUser.setCreatedAt(user.getCreatedAt());

        return repo.save(existingUser);
    }


    public List<Order> getAllOrders() {
        return orderrepo.findAll();
    }

    public void deleteUser(Long id) {
        repo.deleteById(id);
    }

    public Order addOrder(Order order) {

        Long customerId = order.getCustomer().getId();

        UserEntity customer = repo.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found with id: " + customerId));

        order.setCustomer(customer);
        return orderrepo.save(order);

    }

    public Order editOrder(Order order) {
        Long id = order.getId();
        Order oldOrder = orderrepo.findById(id).orElseThrow(() -> new RuntimeException(
                "User not found with id: " + order.getId()));
        oldOrder.setId(order.getId());
        oldOrder.setProductName((order.getProductName()));
        oldOrder.setAmount(order.getAmount());
        oldOrder.setStatus(order.getStatus());
        return orderrepo.save(oldOrder);

    }
}
