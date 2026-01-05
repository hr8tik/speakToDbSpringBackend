package com.example.demoChat.repository;

import com.example.demoChat.entitiy.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends JpaRepository<UserEntity,Integer> {

}
