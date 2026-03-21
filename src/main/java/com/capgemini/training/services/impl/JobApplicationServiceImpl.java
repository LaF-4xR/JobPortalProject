package com.capgemini.training.services.impl;

import com.capgemini.training.dao.*;
import com.capgemini.training.entity.*;
import com.capgemini.training.services.JobApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    @Autowired
    private JobApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Override
    public JobApplication applyToJob(Long jobId, Long userId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        JobSeeker seeker = jobSeekerRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Job seeker not found"));

        boolean alreadyApplied = applicationRepository
                .existsByJob_JobIdAndSeeker_SeekerId(jobId, seeker.getSeekerId());

        if (alreadyApplied) {
            throw new RuntimeException("You have already applied to this job");
        }

        JobApplication application = new JobApplication();
        application.setJob(job);
        application.setSeeker(seeker);

        return applicationRepository.save(application);
    }

    @Override
    public List<JobApplication> getApplicationsBySeeker(Long userId) {

        JobSeeker seeker = jobSeekerRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Job seeker not found"));

        return applicationRepository.findBySeeker_SeekerId(seeker.getSeekerId());
    }
}