package com.capgemini.training.services;

import com.capgemini.training.entity.JobApplication;

import java.util.List;

public interface JobApplicationService {

    JobApplication applyToJob(Long jobId, Long userId);

    List<JobApplication> getApplicationsBySeeker(Long userId);
}