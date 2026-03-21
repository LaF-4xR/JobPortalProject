package com.capgemini.training.restcontroller;

import com.capgemini.training.entity.JobApplication;
import com.capgemini.training.services.JobApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationRestController {

    @Autowired
    private JobApplicationService applicationService;

    @PostMapping("/apply")
    public JobApplication apply(@RequestParam Long jobId,
                                @RequestParam Long userId) {
        return applicationService.applyToJob(jobId, userId);
    }

    @GetMapping("/seeker/{userId}")
    public List<JobApplication> getApplications(@PathVariable Long userId) {
        return applicationService.getApplicationsBySeeker(userId);
    }
}