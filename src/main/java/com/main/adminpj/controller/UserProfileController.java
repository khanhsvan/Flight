package com.main.adminpj.controller;

import com.main.adminpj.model.User;
import com.main.adminpj.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/userprofile")
    public String showUserProfile(HttpSession session, Model model) {
        // Get user info from session
        User user = (User) session.getAttribute("user");

        if (user == null) {
            // If user is not logged in, redirect to login page
            return "redirect:/login";
        }

        // Get user details from database
        User userDetails = userService.getUserDetails(user.getUsername());
        double balance = userDetails.getBalance();
        if (userDetails == null) {
            // If user details are not found, show error message
            model.addAttribute("errorMessage", "User details not found");
            return "error";
        }

        // Add user details to model
        model.addAttribute("user", userDetails);
        model.addAttribute("balance", balance);
        // Show user profile page
        return "userprofile";
    }
}
