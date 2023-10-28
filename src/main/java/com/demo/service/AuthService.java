package com.demo.service;

import com.demo.dto.request.LoginRequest;
import com.demo.dto.request.SignupRequest;
import com.demo.dto.response.JwtResponse;

public interface AuthService {
    JwtResponse authenticateAccount(LoginRequest loginRequest);

    void registerAccount(SignupRequest signupRequest);
}
