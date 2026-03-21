package com.capgemini.training.controller;

import com.capgemini.training.dto.JobRequest;
import com.capgemini.training.entity.AppUser;
import com.capgemini.training.entity.Job;
import com.capgemini.training.entity.UserRole;
import com.capgemini.training.services.JobService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public String allJobs(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("jobs", jobService.searchJobs(keyword));
        model.addAttribute("keyword", keyword);
        return "job-list";
    }

    @GetMapping("/post")
    public String postJobPage(HttpSession session, Model model) {
        AppUser user = (AppUser) session.getAttribute("loggedInUser");
        if (user == null || user.getRole() != UserRole.EMPLOYER) {
            return "redirect:/login";
        }

        model.addAttribute("jobRequest", new JobRequest());
        return "post-job";
    }

    @PostMapping("/post")
    public String postJob(HttpSession session,
                          @ModelAttribute("jobRequest") JobRequest request,
                          Model model) {
        AppUser user = (AppUser) session.getAttribute("loggedInUser");
        if (user == null || user.getRole() != UserRole.EMPLOYER) {
            return "redirect:/login";
        }

        try {
            jobService.createJob(user.getUserId(), request);
            return "redirect:/employer/dashboard?jobAdded=true";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "post-job";
        }
    }

    @GetMapping("/edit/{jobId}")
    public String editJobPage(@PathVariable Long jobId, HttpSession session, Model model) {
        AppUser user = (AppUser) session.getAttribute("loggedInUser");
        if (user == null || user.getRole() != UserRole.EMPLOYER) {
            return "redirect:/login";
        }

        Job job = jobService.getJobById(jobId);
        model.addAttribute("job", job);
        return "edit-job";
    }

    @PostMapping("/edit/{jobId}")
    public String updateJob(@PathVariable Long jobId,
                            @ModelAttribute JobRequest request,
                            HttpSession session,
                            Model model) {
        AppUser user = (AppUser) session.getAttribute("loggedInUser");
        if (user == null || user.getRole() != UserRole.EMPLOYER) {
            return "redirect:/login";
        }

        try {
            jobService.updateJob(jobId, request);
            return "redirect:/employer/dashboard?jobUpdated=true";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("job", jobService.getJobById(jobId));
            return "edit-job";
        }
    }

    @PostMapping("/delete/{jobId}")
    public String deleteJob(@PathVariable Long jobId, HttpSession session) {
        AppUser user = (AppUser) session.getAttribute("loggedInUser");
        if (user == null || user.getRole() != UserRole.EMPLOYER) {
            return "redirect:/login";
        }

        jobService.deleteJob(jobId);
        return "redirect:/employer/dashboard?jobDeleted=true";
    }
}