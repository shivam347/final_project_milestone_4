package com.milestone.four.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.milestone.four.Repository.UserRepository;
import com.milestone.four.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserRepository repo;

    // ================= LOGIN PAGE =================
    @GetMapping("/login")
    public String loginPage(
            @RequestParam(value = "error", required = false) String error,
            Model model) {

        if (error != null) {
            model.addAttribute("error", "Invalid email or password");
        }

        return "login";
    }

    // ================= REGISTER PAGE =================
    @GetMapping("/register")
    public String signupPage(Model model) {

        model.addAttribute("user", new User()); // VERY IMPORTANT

        return "register";
    }

    // ================= REGISTER =================
    @PostMapping("/register")
    public String signup(User user) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Encode password
        user.setPassword(
                encoder.encode(user.getPassword()));

        repo.save(user);

        return "redirect:/login?success";
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public String loginUser(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = repo.findByEmail(email);

        if (user != null && encoder.matches(password, user.getPassword())) {

            System.out.println("LOGIN SUCCESS ✅");

            // Save user in session
            session.setAttribute("loggedUser", user);

            return "redirect:/ui/dashboard";
        }

        // Login failed
        model.addAttribute("error", "Invalid email or password");
        model.addAttribute("email", email);

        return "login";
    }

    // ================= LOGOUT =================
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}