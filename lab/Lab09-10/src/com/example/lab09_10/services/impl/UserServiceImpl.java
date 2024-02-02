package com.example.lab09_10.services.impl;

import com.example.lab09_10.models.MyResponseMessage;
import com.example.lab09_10.models.User;
import com.example.lab09_10.repository.UserRepository;
import com.example.lab09_10.services.UserService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<MyResponseMessage<String>> register(User user) {
        String msg = "";
        if (userRepository.findUserByEmail(user.getEmail()) == null) {
            msg = "Register successfully";
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
        } else {
            msg = "Email has been used";
        }
        MyResponseMessage<String> myResponseMessage = new MyResponseMessage<>(Response.SC_OK, msg);
        return ResponseEntity.ok(myResponseMessage);
    }

    @Override
    public boolean isCorrectEmailAndPassword(User user) {
        User userFound = userRepository.findUserByEmail(user.getEmail());
        if (userFound == null) return false;
        return passwordEncoder.matches(user.getPassword(), userFound.getPassword());

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
