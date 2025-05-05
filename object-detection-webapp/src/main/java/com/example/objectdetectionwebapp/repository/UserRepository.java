package com.example.objectdetectionwebapp.repository;

import com.example.objectdetectionwebapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}