package com.example.hybridbooksbackend.controller;

import com.example.hybridbooksbackend.model.UserEntity;
import com.example.hybridbooksbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> list = userService.getAllUsers();
        return new ResponseEntity<List<UserEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{type}/{value}")
    public ResponseEntity<UserEntity> getUser(@PathVariable("type") String type, @PathVariable("value") String value)
            throws Exception {
        UserEntity entity = userService.getUser(type, value);
        return new ResponseEntity<UserEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) throws Exception {
        UserEntity created = userService.createUser(user);
        return new ResponseEntity<UserEntity>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<UserEntity> updateUser(UserEntity user)
            throws Exception {
        UserEntity updated = userService.updateUser(user);
        return new ResponseEntity<UserEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{type}/{value}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable("type") String type, @PathVariable("value") String value)
            throws Exception {
        UserEntity deleted = userService.deleteUser(type, value);
        return new ResponseEntity<UserEntity>(deleted, new HttpHeaders(), HttpStatus.OK);
    }
}
