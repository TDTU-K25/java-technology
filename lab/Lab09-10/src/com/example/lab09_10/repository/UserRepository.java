package com.example.lab09_10.repository;

import com.example.lab09_10.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);
    User findUserByEmailAndPassword(String email, String password);
}
