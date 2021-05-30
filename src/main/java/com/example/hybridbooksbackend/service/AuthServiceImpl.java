package com.example.hybridbooksbackend.service;

import com.example.hybridbooksbackend.model.User;
import com.example.hybridbooksbackend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthServiceImpl(final UserService userService, final UserRepository userRepository,
                           final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public boolean login(String username, String password) {
        Optional<User> foundUser = this.userRepository.findByUsername(username);
        if (foundUser.isPresent()) { ;
            return bCryptPasswordEncoder.matches(password, foundUser.get().getPassword());
        } else {
            throw new RuntimeException("No such user exists!");
        }
    }

    @Override
    @Transactional
    public User register(User user) {
        Optional<User> foundUser = this.userRepository.findByUsername(user.getUsername());
        if (foundUser.isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            User newUser = this.userService.create(user);
            return newUser;
        } else {
            throw new RuntimeException("A user already exists!");
        }
    }

    @Override
    public void logout() {
        /* should take the token out of the header and log out via that :P */
    }
}
