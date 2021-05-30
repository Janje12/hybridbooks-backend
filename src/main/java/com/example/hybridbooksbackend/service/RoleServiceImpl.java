package com.example.hybridbooksbackend.service;

import com.example.hybridbooksbackend.model.Role;
import com.example.hybridbooksbackend.model.User;
import com.example.hybridbooksbackend.model.UserHasRole;
import com.example.hybridbooksbackend.repository.RoleRepository;
import com.example.hybridbooksbackend.repository.UserHasRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserHasRoleRepository userHasRoleRepository;

    public RoleServiceImpl(final RoleRepository roleRepository, final UserHasRoleRepository userHasRoleRepository) {
        this.roleRepository = roleRepository;
        this.userHasRoleRepository = userHasRoleRepository;
    }

    @Override
    @Transactional
    public User addRoleCustomer(User user) {
        Optional<Role> customerRole = this.roleRepository.findById(Long.parseLong("1"));
        UserHasRole userHasRole = null;
        if (customerRole.isPresent())
            userHasRole = new UserHasRole(user, customerRole.get());
        userHasRole = this.userHasRoleRepository.save(userHasRole);
        user.setRoles(new ArrayList<UserHasRole>());
        user.getRoles().add(userHasRole);
        return user;
    }

    @Override
    @Transactional
    public User addRoleAdmin(User user) {
        Optional<Role> customerRole = this.roleRepository.findById(Long.parseLong("2"));
        UserHasRole userHasRole = null;
        if (customerRole.isPresent())
            userHasRole = new UserHasRole(user, customerRole.get());
        userHasRole = this.userHasRoleRepository.save(userHasRole);
        user.setRoles(new ArrayList<UserHasRole>());
        user.getRoles().add(userHasRole);
        return user;
    }
}
