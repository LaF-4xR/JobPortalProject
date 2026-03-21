package com.capgemini.training.services;

import com.capgemini.training.dto.LoginRequest;
import com.capgemini.training.dto.SignupRequest;
import com.capgemini.training.entity.AppUser;

public interface AuthService {
    void register(SignupRequest request);
    AppUser login(LoginRequest request);
}