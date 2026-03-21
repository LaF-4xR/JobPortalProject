package com.capgemini.training.restcontroller;

import com.capgemini.training.dto.JobRequest;
import com.capgemini.training.entity.Job;
import com.capgemini.training.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobs")
public class JobRestController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJobById(@PathVariable Long jobId) {
        return ResponseEntity.ok(jobService.getJobById(jobId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(@RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(jobService.searchJobs(keyword));
    }

    @GetMapping("/employer/{employerUserId}")
    public ResponseEntity<List<Job>> getJobsByEmployerUserId(@PathVariable Long employerUserId) {
        return ResponseEntity.ok(jobService.getJobsByEmployerUserId(employerUserId));
    }

    @PostMapping("/employer/{employerUserId}")
    public ResponseEntity<?> createJob(@PathVariable Long employerUserId,
                                       @RequestBody JobRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Job created = jobService.createJob(employerUserId, request);
            response.put("success", true);
            response.put("message", "Job created successfully");
            response.put("data", created);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<?> updateJob(@PathVariable Long jobId,
                                       @RequestBody JobRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Job updated = jobService.updateJob(jobId, request);
            response.put("success", true);
            response.put("message", "Job updated successfully");
            response.put("data", updated);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<Map<String, Object>> deleteJob(@PathVariable Long jobId) {
        Map<String, Object> response = new HashMap<>();
        try {
            jobService.deleteJob(jobId);
            response.put("success", true);
            response.put("message", "Job deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}