package com.demo.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email(message = "Invalid email")
    private String email;
    @NotBlank
    @Size(min = 5, max = 40)
    private String password;
    private ProfileRequest profileRequest;
}
