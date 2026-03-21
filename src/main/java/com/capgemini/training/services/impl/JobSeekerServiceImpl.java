package com.capgemini.training.services.impl;

import com.capgemini.training.dao.JobSeekerRepository;
import com.capgemini.training.dto.JobSeekerProfileRequest;
import com.capgemini.training.entity.JobSeeker;
import com.capgemini.training.services.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobSeekerServiceImpl implements JobSeekerService {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Override
    public JobSeeker getSeekerByUserId(Long userId) {
        return jobSeekerRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Job seeker profile not found"));
    }

    @Override
    public List<JobSeeker> getAllSeekers() {
        return jobSeekerRepository.findAll();
    }

    @Override
    @Transactional
    public JobSeeker updateProfile(Long userId, JobSeekerProfileRequest request) {
        JobSeeker seeker = jobSeekerRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Job seeker profile not found"));

        seeker.setName(request.getName());
        seeker.setEmail(request.getEmail());
        seeker.setPhone(request.getPhone());
        seeker.setSkills(request.getSkills());
        seeker.setExperience(request.getExperience());

        return jobSeekerRepository.save(seeker);
    }
}