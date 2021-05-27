package com.example.hybridbooksbackend.service;

import com.example.hybridbooksbackend.model.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity createUser(UserEntity user) throws Exception;
    List<UserEntity> getAllUsers();
    UserEntity getUser(String type, String value) throws Exception;
    UserEntity updateUser(UserEntity user) throws Exception;
    UserEntity deleteUser(String type, String value) throws Exception;
}
