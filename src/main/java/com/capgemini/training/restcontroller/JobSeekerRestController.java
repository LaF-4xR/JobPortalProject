package com.capgemini.training.restcontroller;

import com.capgemini.training.dto.JobSeekerProfileRequest;
import com.capgemini.training.entity.JobSeeker;
import com.capgemini.training.services.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seekers")
public class JobSeekerRestController {

    @Autowired
    private JobSeekerService jobSeekerService;

    @GetMapping
    public ResponseEntity<List<JobSeeker>> getAllSeekers() {
        return ResponseEntity.ok(jobSeekerService.getAllSeekers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<JobSeeker> getSeekerByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(jobSeekerService.getSeekerByUserId(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateSeekerProfile(@PathVariable Long userId,
                                                 @RequestBody JobSeekerProfileRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            JobSeeker updated = jobSeekerService.updateProfile(userId, request);
            response.put("success", true);
            response.put("message", "Job seeker profile updated successfully");
            response.put("data", updated);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}