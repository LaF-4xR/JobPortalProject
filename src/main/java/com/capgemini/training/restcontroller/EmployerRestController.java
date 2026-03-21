package com.capgemini.training.restcontroller;

import com.capgemini.training.dto.EmployerProfileRequest;
import com.capgemini.training.entity.Employer;
import com.capgemini.training.services.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employers")
public class EmployerRestController {

    @Autowired
    private EmployerService employerService;

    @GetMapping
    public ResponseEntity<List<Employer>> getAllEmployers() {
        return ResponseEntity.ok(employerService.getAllEmployers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Employer> getEmployerByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(employerService.getEmployerByUserId(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateEmployerProfile(@PathVariable Long userId,
                                                   @RequestBody EmployerProfileRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Employer updated = employerService.updateProfile(userId, request);
            response.put("success", true);
            response.put("message", "Employer profile updated successfully");
            response.put("data", updated);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}