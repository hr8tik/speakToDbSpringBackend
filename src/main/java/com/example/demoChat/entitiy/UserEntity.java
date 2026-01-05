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

@Table(name = "customers")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String city;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "created_at")
    private LocalDate createdAt;

    // getters & setters
}
