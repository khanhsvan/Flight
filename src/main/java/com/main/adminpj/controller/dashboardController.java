package com.main.adminpj.controller;

import com.main.adminpj.model.User;
import com.main.adminpj.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;

@Controller
public class dashboardController {

    @Autowired
    private UserService userService;

    private User getUserFromSession(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            // If user is not logged in, redirect to login page
            return null;
        }

        // Get user details from database
        User userDetails = userService.getUserDetails(user.getUsername());

        if (userDetails == null) {
            // If user details are not found, show error message
            model.addAttribute("error", "không tìm thấy thông tin người dùng vui lòngl ien hệ trung tâm trợ giúp");
            return null;
        }

        // Add user details to model
        model.addAttribute("user", userDetails);

        return userDetails;
    }

    @RequestMapping(value = "/userdashboard", method = RequestMethod.GET)
    public String userDashboard(Model model, HttpSession session) {
        User user = getUserFromSession(session, model);

        if (user == null) {
            // User not logged in or user details not found
            model.addAttribute("error", "vui lòng đăng nhập trước khi tiếp tục");
            return "login";
        }
        BigDecimal balance = (BigDecimal) session.getAttribute("balance");
        session.setAttribute("user", user);
        model.addAttribute("balance", balance);
        model.addAttribute("user", user);

        // Show user dashboard page
        return "userdashboard";
    }

    @GetMapping("/dashboardprofile")
    public String showUserProfile(HttpSession session, Model model) {
        User user = getUserFromSession(session, model);

        if (user == null) {
            // User not logged in or user details not found
            model.addAttribute("error", "vui lòng đăng nhập trước khi tiếp tục");
            return "login";
        }

        // Show user profile page
        return "dashboard";
    }
}
