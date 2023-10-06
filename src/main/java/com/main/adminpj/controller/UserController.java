package com.main.adminpj.controller;

import com.main.adminpj.dao.UserDao;
import com.main.adminpj.model.User;
import com.main.adminpj.service.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDao userDao;

    @RequestMapping("/users")
    public ModelAndView getUsers() {
        List<User> users = userDao.getAllUsers();
        ModelAndView modelAndView = new ModelAndView("admin/users");
        modelAndView.addObject("users", users);

        return modelAndView;
    }

    @GetMapping("/users/edit/{username}")
    public String editUserForm(@PathVariable("username") String username, Model model) {
        String sql = "SELECT * FROM users WHERE username = ?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserRowMapper());
        model.addAttribute("user", user);
        return "admin/editUserForm";
    }

    @PostMapping("/users/edit/{username}")
    public String updateUser(@PathVariable("username") String username, @ModelAttribute("user") User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setUsername(username);
            return "admin/editUserForm";
        }

        String sql = "UPDATE users SET name=?, email=?, password=?, id_card=? WHERE username=?";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getIdCard(), username);

        return "redirect:/users";
    }

    @GetMapping("/users/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/addUserForm";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/addUserForm";
        }

        String sql = "INSERT INTO users (username, name, email, password, id_card) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getName(), user.getEmail(), user.getPassword(), user.getIdCard());

        return "redirect:/users";
    }

    @GetMapping("/users/delete/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        String sql = "DELETE FROM users WHERE username = ?";
        jdbcTemplate.update(sql, username);

        return "redirect:/users";
    }
    @GetMapping("/users/search")
    public String searchUser(@RequestParam("username") String username, Model model) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{username}, new UserRowMapper());

        if (users.isEmpty()) {
            // User not found, show error message
            model.addAttribute("errorMessage", "User not found.");
        } else {
            // User found, show the user details
            model.addAttribute("users", users);
        }

        return "admin/users";
    }

}
