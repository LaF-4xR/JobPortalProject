package com.capgemini.training.services;

import com.capgemini.training.dto.JobSeekerProfileRequest;
import com.capgemini.training.entity.JobSeeker;

import java.util.List;

public interface JobSeekerService {
    JobSeeker getSeekerByUserId(Long userId);
    List<JobSeeker> getAllSeekers();
    JobSeeker updateProfile(Long userId, JobSeekerProfileRequest request);
}