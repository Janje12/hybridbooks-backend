package com.example.hybridbooksbackend.service;

import com.example.hybridbooksbackend.model.User;

public interface RoleService {
    User addRoleCustomer(User user);
    User addRoleAdmin(User user);
}
