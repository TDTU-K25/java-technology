package com.example.lab09_10.services;

import com.example.lab09_10.models.MyResponseMessage;
import com.example.lab09_10.models.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<MyResponseMessage<String>> register(User user);

    boolean isCorrectEmailAndPassword(User user);

    User findByEmail(String email);
}
