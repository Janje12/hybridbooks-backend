package com.example.hybridbooksbackend.service;

import com.example.hybridbooksbackend.exception.EntityNotFoundException;
import com.example.hybridbooksbackend.model.User;
import com.example.hybridbooksbackend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(final UserRepository userRepository, final RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public User create(User user) {
        User foundUser = null;
        if (user.getId() != null)
            foundUser = this.get(user.getId());
        if (foundUser == null) {
            user = this.userRepository.save(user);
            user = this.roleService.addRoleCustomer(user);
            User newUser = this.userRepository.save(user);
            return newUser;
        } else {
            throw new EntityNotFoundException("User already exists!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User get(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent())
            return user.get();
        else
            throw new EntityNotFoundException("No user with id: " + id);
    }

    /* Don't set the password bad security design =) */
    @Override
    @Transactional
    public User update(User user) {
        User foundUser = this.get(user.getId());
        if (foundUser != null) {
            User newUser = new User(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(),
                    user.getPassword(), user.getEmail());
            newUser = this.userRepository.save(newUser);
            return newUser;
        } else {
            throw new EntityNotFoundException("No such user exists!");
        }
    }

    @Override
    public void delete(Long id) {
        User user = this.get(id);
        if (user != null) {
            User deletedUser = user;
            this.userRepository.deleteById(deletedUser.getId());
        } else {
            throw new EntityNotFoundException("No user with id: " + id);
        }
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        if (user.isPresent())
            return user.get();
        else
            throw new EntityNotFoundException("No user with username: " + username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findByUsername(username);
        return new
                org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
