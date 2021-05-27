package com.example.hybridbooksbackend.service;

import com.example.hybridbooksbackend.model.UserEntity;
import com.example.hybridbooksbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity login(String username, String password) {
        Optional<UserEntity> foundUser = this.userRepository.findByUsername(username);
        if (foundUser.isPresent()) {
            UserEntity user = foundUser.get();
            return user.getPassword().equals(password) ? user : null;
        } else {
            throw new Error("No such user exists!");
        }
    }


    /* Bolji nacin da proveri po svemu da li postoji neki? Ili samo po ID proveri */
    @Override
    public UserEntity register(UserEntity user) {
        Optional<UserEntity> foundUser = this.userRepository.findByUsername(user.getUsername());
        if (foundUser.isEmpty()) {
            UserEntity newUser = this.userRepository.save(user);
            return newUser;
        } else {
            throw new Error("A user already exists!");
        }
    }

    @Override
    public void logout() {
        /* should take the token out of the header and log out via that :P */
    }
}
