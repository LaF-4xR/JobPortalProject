package com.capgemini.training.services;

import com.capgemini.training.dto.JobRequest;
import com.capgemini.training.entity.Job;

import java.util.List;

public interface JobService {
    Job createJob(Long employerUserId, JobRequest request);
    List<Job> getAllJobs();
    List<Job> getJobsByEmployerUserId(Long employerUserId);
    Job getJobById(Long jobId);
    Job updateJob(Long jobId, JobRequest request);
    void deleteJob(Long jobId);
    List<Job> searchJobs(String keyword);
}