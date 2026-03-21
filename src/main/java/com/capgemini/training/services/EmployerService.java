package com.capgemini.training.services;

import com.capgemini.training.dto.EmployerProfileRequest;
import com.capgemini.training.entity.Employer;

import java.util.List;

public interface EmployerService {
    Employer getEmployerByUserId(Long userId);
    List<Employer> getAllEmployers();
    Employer updateProfile(Long userId, EmployerProfileRequest request);
}