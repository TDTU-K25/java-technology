package com.example.springcommerce.repository;

import com.example.springcommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);
}
