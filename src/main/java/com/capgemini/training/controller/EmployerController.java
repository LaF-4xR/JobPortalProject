package com.capgemini.training.controller;

import com.capgemini.training.dto.EmployerProfileRequest;
import com.capgemini.training.entity.AppUser;
import com.capgemini.training.entity.Employer;
import com.capgemini.training.entity.UserRole;
import com.capgemini.training.services.EmployerService;
import com.capgemini.training.services.JobService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employer")
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @Autowired
    private JobService jobService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        AppUser user = (AppUser) session.getAttribute("loggedInUser");
        if (user == null || user.getRole() != UserRole.EMPLOYER) {
            return "redirect:/login";
        }

        Employer employer = employerService.getEmployerByUserId(user.getUserId());
        model.addAttribute("employer", employer);
        model.addAttribute("jobs", jobService.getJobsByEmployerUserId(user.getUserId()));
        return "employer-dashboard";
    }

    @GetMapping("/profile")
    public String profilePage(HttpSession session, Model model) {
        AppUser user = (AppUser) session.getAttribute("loggedInUser");
        if (user == null || user.getRole() != UserRole.EMPLOYER) {
            return "redirect:/login";
        }

        model.addAttribute("employer", employerService.getEmployerByUserId(user.getUserId()));
        return "employer-profile";
    }

    @PostMapping("/profile")
    public String updateProfile(HttpSession session,
                                @ModelAttribute EmployerProfileRequest request,
                                Model model) {
        AppUser user = (AppUser) session.getAttribute("loggedInUser");
        if (user == null || user.getRole() != UserRole.EMPLOYER) {
            return "redirect:/login";
        }

        try {
            employerService.updateProfile(user.getUserId(), request);
            return "redirect:/employer/dashboard?updated=true";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("employer", employerService.getEmployerByUserId(user.getUserId()));
            return "employer-profile";
        }
    }
}