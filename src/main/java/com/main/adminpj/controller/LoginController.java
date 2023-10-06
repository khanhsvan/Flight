package com.main.adminpj.controller;

import com.main.adminpj.model.User;
import com.main.adminpj.service.UserRowMapper;
import com.main.adminpj.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class LoginController {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String processLoginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   Model model,
                                   HttpSession session) {
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        List<User> users = jdbcTemplate.query(query, new Object[]{username, password}, new UserRowMapper());
        BigDecimal balance = userService.getBalance(username);
        if (users.isEmpty()) {
            // User not found, show error message
            model.addAttribute("error", "Tài khoản hoặc mâtj khẩu không đúng");
            return "login";
        } else {
            // User authenticated, redirect to appropriate page

            User user = users.get(0);
            session.setAttribute("user", user);
            session.setAttribute("balance", balance);
            if (user.getRole().equals("admin")) {
                // Admin user, redirect to admin page
                return "redirect:/dashboardprofile";
            } else {
                // Normal user, redirect to home page
                return "redirect:/userdashboard";
            }
        }
    }



}
