package com.main.adminpj.controller;

import com.main.adminpj.model.User;
import com.main.adminpj.service.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class SignUpController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @GetMapping("/signup")
    public String processSignUpForm(){
        return "signup";
    }
    @PostMapping("/signup")
    public String processSignUpForm(@RequestParam("username") String username,
                                    @RequestParam("password") String password,
                                    @RequestParam("email") String email,
                                    Model model) {
        // Check if the username is already taken
        String query = "SELECT * FROM users WHERE username=?";
        List<User> users = jdbcTemplate.query(query, new Object[]{username}, new UserRowMapper());

        if (!users.isEmpty()) {
            // Username already taken, show error message
            model.addAttribute("error", "Tên đăng nhập đã được sử dụng");
            return "signup";
        } else {
            // Create a new user with the provided username and password
            query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            int rowsAffected = jdbcTemplate.update(query, username, password, email);

            if (rowsAffected == 1) {
                // User created successfully, show success message
                model.addAttribute("success", "Tạo tài khoản thành công");
                return "login";
            } else {
                // Something went wrong, show error message
                model.addAttribute("error", "Đã có lỗi xảy ra");
                return "signup";
            }
        }
    }

}
