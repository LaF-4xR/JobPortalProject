package com.capgemini.training.services.impl;

import com.capgemini.training.dao.EmployerRepository;
import com.capgemini.training.dto.EmployerProfileRequest;
import com.capgemini.training.entity.Employer;
import com.capgemini.training.services.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    @Override
    public Employer getEmployerByUserId(Long userId) {
        return employerRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Employer profile not found"));
    }

    @Override
    public List<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }

    @Override
    @Transactional
    public Employer updateProfile(Long userId, EmployerProfileRequest request) {
        Employer employer = employerRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Employer profile not found"));

        employer.setName(request.getName());
        employer.setCompanyName(request.getCompanyName());
        employer.setEmail(request.getEmail());
        employer.setPhone(request.getPhone());
        employer.setLocation(request.getLocation());

        return employerRepository.save(employer);
    }
}