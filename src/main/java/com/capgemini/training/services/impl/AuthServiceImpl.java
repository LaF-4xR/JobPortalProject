package com.capgemini.training.services.impl;

import com.capgemini.training.dao.AppUserRepository;
import com.capgemini.training.dao.EmployerRepository;
import com.capgemini.training.dao.JobSeekerRepository;
import com.capgemini.training.dto.LoginRequest;
import com.capgemini.training.dto.SignupRequest;
import com.capgemini.training.entity.AppUser;
import com.capgemini.training.entity.Employer;
import com.capgemini.training.entity.JobSeeker;
import com.capgemini.training.entity.UserRole;
import com.capgemini.training.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Override
    @Transactional
    public void register(SignupRequest request) {
        if (appUserRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        UserRole role;
        try {
            role = UserRole.valueOf(request.getRole().toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Invalid role");
        }

        AppUser user = new AppUser();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(role);

        AppUser savedUser = appUserRepository.save(user);

        if (role == UserRole.EMPLOYER) {
            Employer employer = new Employer();
            employer.setName(request.getName());
            employer.setCompanyName(request.getCompanyName());
            employer.setEmail(request.getEmail());
            employer.setPhone(request.getPhone());
            employer.setLocation(request.getLocation());
            employer.setUser(savedUser);
            employerRepository.save(employer);
        } else if (role == UserRole.SEEKER) {
            JobSeeker seeker = new JobSeeker();
            seeker.setName(request.getName());
            seeker.setEmail(request.getEmail());
            seeker.setPhone(request.getPhone());
            seeker.setSkills(request.getSkills());
            seeker.setExperience(request.getExperience());
            seeker.setUser(savedUser);
            jobSeekerRepository.save(seeker);
        }
    }

    @Override
    public AppUser login(LoginRequest request) {
        AppUser user = appUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return user;
    }
}