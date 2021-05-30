package com.example.hybridbooksbackend.service;

import com.example.hybridbooksbackend.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User create(User user);
    List<User> getAll();
    User get(Long id);
    User update(User user);
    void delete(Long id);
    User findByUsername(String username);
}
