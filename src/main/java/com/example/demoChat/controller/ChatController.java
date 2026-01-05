package com.example.demoChat.controller;

import com.example.demoChat.entitiy.Order;
import com.example.demoChat.entitiy.UserEntity;
import com.example.demoChat.service.AIQueryService;
import com.example.demoChat.service.DatabaseQueryService;
import com.example.demoChat.service.JPQLValidator;
import com.example.demoChat.service.userService;
import org.apache.catalina.User;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(
        origins = "http://localhost:8081",   // React / frontend
        allowedHeaders = "*",
        methods = {
                org.springframework.web.bind.annotation.RequestMethod.GET,
                org.springframework.web.bind.annotation.RequestMethod.POST,
                org.springframework.web.bind.annotation.RequestMethod.PUT,
                org.springframework.web.bind.annotation.RequestMethod.DELETE,
                org.springframework.web.bind.annotation.RequestMethod.OPTIONS
        }
)
@RestController
public class ChatController {

    private final AIQueryService aiQueryService;
    private final DatabaseQueryService dbService;
    private final JPQLValidator validator;

    @Autowired
    private userService service;



    public ChatController(
            AIQueryService aiQueryService,
            DatabaseQueryService dbService,
            JPQLValidator validator) {
        this.aiQueryService = aiQueryService;
        this.dbService = dbService;
        this.validator = validator;
    }


    @GetMapping("/")
    String hi (){
        return "hello";
    }

   @GetMapping("/get")
        public List<UserEntity> getData(){

        return service.getAll();
        }

    @GetMapping("/getOrders")
    public List<Order> getOrderData(){

        return service.getAllOrders();
    }

   @PostMapping("/post")
   public UserEntity post(@RequestBody UserEntity user){
        return service.postData(user);
   }

    @PostMapping("/ask-db")
    public Object askDatabase(@RequestBody String question) {

        String jpql = aiQueryService.generateJPQL(question);


        return dbService.execute(jpql);
    }



}
