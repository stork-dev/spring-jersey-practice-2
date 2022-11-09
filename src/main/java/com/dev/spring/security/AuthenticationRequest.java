package com.dev.spring.security;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder(toBuilder = true)
@Getter
public class AuthenticationRequest {
    @NotBlank(message = "username is mandatory")
    private String username;
    @NotBlank(message = "password is mandatory")
    private String password;

}
