package com.capgemini.training.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_applications",
       uniqueConstraints = @UniqueConstraint(columnNames = {"job_id", "seeker_id"}))
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ManyToOne
    @JoinColumn(name = "seeker_id", nullable = false)
    private JobSeeker seeker;

    private LocalDateTime appliedAt;

    @PrePersist
    public void prePersist() {
        this.appliedAt = LocalDateTime.now();
    }

    // getters & setters
    public Long getApplicationId() { return applicationId; }

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }

    public JobSeeker getSeeker() { return seeker; }
    public void setSeeker(JobSeeker seeker) { this.seeker = seeker; }

    public LocalDateTime getAppliedAt() { return appliedAt; }
}