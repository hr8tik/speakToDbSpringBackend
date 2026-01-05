package com.example.demoChat.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private Double amount;

    private String status; // PLACED, SHIPPED, CANCELLED

    @Column(name = "order_date")
        private LocalDate orderDate;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "customer_id")
        private UserEntity customer;

        // getters & setters
    }
