package com.example.lab09_10.controllers;

import com.example.lab09_10.models.MyResponseMessage;
import com.example.lab09_10.models.User;
import com.example.lab09_10.services.UserService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;

@RestController
@RequestMapping("/api/account")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    RoleService roleService;

    @PostMapping("/register")
    public ResponseEntity<MyResponseMessage<String>> register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<MyResponseMessage<String>> login(@RequestBody User user) {
        String msg = "";
        if (userService.isCorrectEmailAndPassword(user)) {
            Authentication authentication = authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            msg = "Login successfully";
        } else {
            msg = "Username or password invalid! Please check again";
        }
        MyResponseMessage<String> myResponseMessage = new MyResponseMessage<>(Response.SC_OK, msg);
        return ResponseEntity.ok(myResponseMessage);
    }
}
