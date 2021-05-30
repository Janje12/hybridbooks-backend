package com.example.hybridbooksbackend.service;

import com.example.hybridbooksbackend.model.User;

public interface AuthService {
    boolean login(String username, String password);
    User register(User user);
    void logout();
}
