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
import org.springframework.http.ResponseEntity;
import java.util.List;

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

   @PostMapping("/updateUser")
   public ResponseEntity<UserEntity> updateUser(
           @RequestBody UserEntity user) {

       UserEntity updatedUser = service.updateUser(user);
       return ResponseEntity.ok(updatedUser);
   }


    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.badRequest()
                    .body("User ID must be provided");
        }

        service.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PostMapping("/addorders")
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        Order newOrder = service.addOrder(order);
        return ResponseEntity.ok(newOrder);
    }

    @PostMapping("/editOrders")
    public ResponseEntity<Order> editOrders(@RequestBody Order order){
        Order newOrder = service.editOrder(order);
        return ResponseEntity.ok(newOrder);
    }


    @PostMapping("/ask-db")
    public Object askDatabase(@RequestBody String question) {

        String jpql = aiQueryService.generateJPQL(question);

        return dbService.execute(jpql);

        
    }



}
