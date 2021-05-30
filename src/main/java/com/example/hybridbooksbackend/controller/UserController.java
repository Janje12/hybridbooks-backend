package com.example.hybridbooksbackend.controller;

import com.example.hybridbooksbackend.dto.user.UserCreateDto;
import com.example.hybridbooksbackend.dto.user.UserUpdateDto;
import com.example.hybridbooksbackend.dto.user.UserViewDto;
import com.example.hybridbooksbackend.model.User;
import com.example.hybridbooksbackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(final UserService userService, final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserViewDto> getAllUsers() {
        List<User> userEntities = userService.getAll();
        List<UserViewDto> userDtos = userEntities.stream()
                .map(b -> modelMapper.map(b, UserViewDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserViewDto getUser(@PathVariable("id") Long id) {
        User userEntity = userService.get(id);
        UserViewDto userDto = modelMapper.map(userEntity, UserViewDto.class);
        return userDto;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserCreateDto createUser(@RequestBody UserCreateDto userDto) {
        User userEntity = modelMapper.map(userDto, User.class);
        userEntity = userService.create(userEntity);
        userDto = modelMapper.map(userEntity, UserCreateDto.class);
        return userDto;
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserUpdateDto updateUser(@RequestBody UserUpdateDto userDto) {
        User userEntity = modelMapper.map(userDto, User.class);
        userEntity = userService.update(userEntity);
        userDto = modelMapper.map(userEntity, UserUpdateDto.class);
        return userDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
