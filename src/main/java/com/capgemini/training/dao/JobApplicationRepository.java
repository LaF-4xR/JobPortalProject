package com.capgemini.training.dao;

import com.capgemini.training.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    boolean existsByJob_JobIdAndSeeker_SeekerId(Long jobId, Long seekerId);

    List<JobApplication> findBySeeker_SeekerId(Long seekerId);

    List<JobApplication> findByJob_JobId(Long jobId);
}