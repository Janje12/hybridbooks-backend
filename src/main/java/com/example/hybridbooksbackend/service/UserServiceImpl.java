package com.example.hybridbooksbackend.service;

import com.example.hybridbooksbackend.model.UserEntity;
import com.example.hybridbooksbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity createUser(UserEntity user) throws Exception {
        UserEntity foundUser = null;
        if (user.getIdUser() != 0)
            foundUser = this.getUser("id", user.getIdUser() + "");
        if (foundUser == null) {
            System.err.println(user);
            UserEntity newUser = this.userRepository.save(user);
            return newUser;
        } else {
            throw new Exception("User already exists!");
        }
    }

    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> userList = userRepository.findAll();
        if (userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<UserEntity>();
        }
    }

    @Override
    public UserEntity getUser(String type, String value) throws Exception {
        Optional<UserEntity> user = Optional.empty();
        if (type.equals("id"))
            user = this.userRepository.findById(Long.parseLong(value));
        else if (type.equals("username"))
            user = this.userRepository.findByUsername(value);
        else if (type.equals("firstName"))
            user = this.userRepository.findByFirstName(value);
        else if (type.equals("lastName"))
            user = this.userRepository.findByLastName(value);
        else if (type.equals("email"))
            user = this.userRepository.findByEmail(value);
        else if (type.equals("role"))
            user = this.userRepository.findByRole(value);

        if (user.isPresent())
            return user.get();
        else
            throw new Exception("No user with " + type + ": " + value);
    }

    /* Don't set the password bad security design =) */
    @Override
    public UserEntity updateUser(UserEntity user) throws Exception {
        UserEntity foundUser = this.getUser("id", user.getIdUser() + "");
        if (foundUser != null) {
            UserEntity newUser = foundUser;
            newUser.setEmail(user.getEmail());
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setUsername(user.getUsername());
            newUser.setRole(user.getRole());

            newUser = this.userRepository.save(newUser);
            return newUser;
        } else {
            throw new Exception("No such user exists!");
        }
    }

    @Override
    public UserEntity deleteUser(String type, String value) throws Exception {
        UserEntity user = this.getUser(type, value);
        if (user != null) {
            UserEntity deletedUser = user;
            this.userRepository.deleteById(deletedUser.getIdUser());
            return user;
        } else {
            throw new Exception("No user with " + type + ": " + value);
        }
    }
}
