package com.capgemini.training.services.impl;

import com.capgemini.training.dao.EmployerRepository;
import com.capgemini.training.dao.JobRepository;
import com.capgemini.training.dto.JobRequest;
import com.capgemini.training.entity.Employer;
import com.capgemini.training.entity.Job;
import com.capgemini.training.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Override
    @Transactional
    public Job createJob(Long employerUserId, JobRequest request) {
        Employer employer = employerRepository.findByUser_UserId(employerUserId)
                .orElseThrow(() -> new RuntimeException("Employer profile not found"));

        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setSalary(request.getSalary());
        job.setLocation(request.getLocation());
        job.setEmployer(employer);

        return jobRepository.save(job);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> getJobsByEmployerUserId(Long employerUserId) {
        return jobRepository.findAll()
                .stream()
                .filter(job -> job.getEmployer() != null
                        && job.getEmployer().getUser() != null
                        && job.getEmployer().getUser().getUserId().equals(employerUserId))
                .collect(Collectors.toList());
    }

    @Override
    public Job getJobById(Long jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }

    @Override
    @Transactional
    public Job updateJob(Long jobId, JobRequest request) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setSalary(request.getSalary());
        job.setLocation(request.getLocation());

        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(Long jobId) {
        if (!jobRepository.existsById(jobId)) {
            throw new RuntimeException("Job not found");
        }
        jobRepository.deleteById(jobId);
    }

    @Override
    public List<Job> searchJobs(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return jobRepository.findAll();
        }

        String key = keyword.toLowerCase();

        return jobRepository.findAll()
                .stream()
                .filter(job ->
                        (job.getTitle() != null && job.getTitle().toLowerCase().contains(key)) ||
                        (job.getLocation() != null && job.getLocation().toLowerCase().contains(key)) ||
                        (job.getDescription() != null && job.getDescription().toLowerCase().contains(key))
                )
                .collect(Collectors.toList());
    }
}