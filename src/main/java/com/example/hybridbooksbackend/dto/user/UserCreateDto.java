package com.example.hybridbooksbackend.dto.user;

import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserCreateDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
}
