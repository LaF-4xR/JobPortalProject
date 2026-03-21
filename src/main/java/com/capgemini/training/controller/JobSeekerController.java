package com.capgemini.training.controller;

import com.capgemini.training.entity.AppUser;
import com.capgemini.training.entity.JobSeeker;
import com.capgemini.training.entity.UserRole;
import com.capgemini.training.services.JobService;
import com.capgemini.training.services.JobSeekerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/seeker")
public class JobSeekerController {

    @Autowired
    private JobSeekerService jobSeekerService;

    @Autowired
    private JobService jobService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        AppUser user = (AppUser) session.getAttribute("loggedInUser");
        if (user == null || user.getRole() != UserRole.SEEKER) {
            return "redirect:/login";
        }

        JobSeeker seeker = jobSeekerService.getSeekerByUserId(user.getUserId());
        model.addAttribute("seeker", seeker);
        model.addAttribute("jobs", jobService.getAllJobs());
        return "seeker-dashboard";
    }

    @GetMapping("/profile")
    public String profilePage(HttpSession session, Model model) {
        AppUser user = (AppUser) session.getAttribute("loggedInUser");
        if (user == null || user.getRole() != UserRole.SEEKER) {
            return "redirect:/login";
        }

        model.addAttribute("seeker", jobSeekerService.getSeekerByUserId(user.getUserId()));
        return "seeker-profile";
    }
}