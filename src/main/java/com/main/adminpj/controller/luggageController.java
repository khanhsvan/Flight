//package com.main.adminpj.controller;
//
//import com.main.adminpj.model.luggage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@Controller
//public class luggageController {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @GetMapping("/luggage")
////    public String viewLuggage(Model model) {
//        String query = "SELECT * FROM luggage";
//        List<luggage> luggage = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(luggage.class));
//        model.addAttribute("luggages", luggage);
//        return "admin/luggage";
//    }
//}
