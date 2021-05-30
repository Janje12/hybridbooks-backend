package com.example.hybridbooksbackend.dto.user;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserAuthDto {
    private String username;
    private String password;

}
