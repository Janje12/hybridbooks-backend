package com.example.hybridbooksbackend.service;

import com.example.hybridbooksbackend.model.UserEntity;

public interface AuthService {
    UserEntity login(String username, String password);
    UserEntity register(UserEntity user);
    void logout();
}
