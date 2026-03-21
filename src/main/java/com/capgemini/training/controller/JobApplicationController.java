package com.capgemini.training.controller;

import com.capgemini.training.entity.AppUser;
import com.capgemini.training.entity.UserRole;
import com.capgemini.training.services.JobApplicationService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationService applicationService;

    @PostMapping("/apply/{jobId}")
    public String apply(@PathVariable Long jobId, HttpSession session) {

        AppUser user = (AppUser) session.getAttribute("loggedInUser");

        if (user == null || user.getRole() != UserRole.SEEKER) {
            return "redirect:/login";
        }

        try {
            applicationService.applyToJob(jobId, user.getUserId());
            return "redirect:/seeker/dashboard?applied=true";
        } catch (Exception e) {
            return "redirect:/seeker/dashboard?error=" + e.getMessage();
        }
    }
}