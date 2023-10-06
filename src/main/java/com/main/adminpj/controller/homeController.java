package com.main.adminpj.controller;

import com.main.adminpj.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
    @GetMapping("/")
    public String home(HttpSession session) {
        User user = (User) session.getAttribute("user");

        // Kiểm tra xem có thông tin người dùng trong session hay không
        if (user != null) {
            // Nếu có thông tin người dùng, chuyển hướng đến trang của người dùng
            return "redirect:/userdashboard";
        } else {
            // Nếu không có thông tin người dùng, chuyển hướng đến trang Home mặc định
            return "index";
        }
    }

}
