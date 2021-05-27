package com.example.hybridbooksbackend.controller;

import com.example.hybridbooksbackend.model.UserEntity;
import com.example.hybridbooksbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> login(String username, String password) {
        UserEntity user = this.authService.login(username, password);
        return new ResponseEntity<UserEntity>(user, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> register(UserEntity user) {
        UserEntity created = this.authService.register(user);
        return new ResponseEntity<UserEntity>(created, new HttpHeaders(), HttpStatus.OK);
    }
}
