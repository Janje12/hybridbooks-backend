package com.example.hybridbooksbackend.controller;

import com.example.hybridbooksbackend.dto.user.UserAuthDto;
import com.example.hybridbooksbackend.dto.user.UserCreateDto;
import com.example.hybridbooksbackend.model.User;
import com.example.hybridbooksbackend.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final AuthService authService;
    private final ModelMapper modelMapper;

    public AuthController(final AuthService authService, final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.authService = authService;
    }

    /* Useless? */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody UserAuthDto userDto) {
        User userEntity = modelMapper.map(userDto, User.class);
        boolean test = this.authService.login(userEntity.getUsername(), userEntity.getPassword());
        userDto = modelMapper.map(userEntity, UserAuthDto.class);
        return test + "";
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserCreateDto register(@RequestBody UserCreateDto userDto) {
        User userEntity = modelMapper.map(userDto, User.class);
        userEntity = this.authService.register(userEntity);
        userDto = modelMapper.map(userEntity, UserCreateDto.class);
        return userDto;
    }

}
