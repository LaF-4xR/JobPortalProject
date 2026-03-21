package com.capgemini.training.controller;

import com.capgemini.training.dto.LoginRequest;
import com.capgemini.training.dto.SignupRequest;
import com.capgemini.training.entity.AppUser;
import com.capgemini.training.entity.UserRole;
import com.capgemini.training.services.AuthService;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    // ================= LOGIN PAGE =================
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    // ================= SIGNUP PAGE =================
    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("signupRequest", new SignupRequest());
        return "signup";
    }

    // ================= SIGNUP =================
    @PostMapping("/signup")
    public String signup(@ModelAttribute("signupRequest") SignupRequest request,
                         Model model,
                         HttpSession session) {
        try {
            authService.register(request);

            // ✅ store message in session
            session.setAttribute("successMessage", "Registration successful. Please login.");

            return "redirect:/login";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public String login(@ModelAttribute("loginRequest") LoginRequest request,
                        HttpSession session,
                        Model model) {
        try {
            AppUser user = authService.login(request);

            session.setAttribute("loggedInUser", user);

            if (user.getRole() == UserRole.EMPLOYER) {
                return "redirect:/employer/dashboard";
            } else if (user.getRole() == UserRole.SEEKER) {
                return "redirect:/seeker/dashboard";
            } else {
                return "redirect:/jobs";
            }

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    // ================= LOGOUT =================
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        // invalidate old session
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }

        // create new session for message
        HttpSession newSession = request.getSession(true);
        newSession.setAttribute("successMessage", "You have been logged out successfully.");

        return "redirect:/login";
    }
}