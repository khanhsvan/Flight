package com.main.adminpj.controller;

import com.main.adminpj.entity.ThanhToan;
import com.main.adminpj.service.ThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ThanhToanController {

    @Autowired
    private ThanhToanService thanhToanService;

    @GetMapping("/thanhtoan")
    public String getThanhToanList(Model model) {
        List<ThanhToan> thanhToanList = thanhToanService.getAllThanhToan();
        model.addAttribute("thanhToanList", thanhToanList);
        return "admin/thanhToanList";
    }

    @GetMapping("/thanhtoan/{id}")
    public String getThanhToanById(@PathVariable("id") int id, Model model) {
        ThanhToan thanhToan = thanhToanService.getThanhToanById(id);
        model.addAttribute("thanhToan", thanhToan);
        return "admin/thanhToanDetail";
    }

    @GetMapping("/thanhtoan/edit/{id}")
    public String editThanhToanForm(@PathVariable("id") int id, Model model) {
        ThanhToan thanhToan = thanhToanService.getThanhToanById(id);
        model.addAttribute("thanhToan", thanhToan);
        return "admin/editThanhToanForm";
    }

    @PostMapping("/thanhtoan/update/{id}")
    public String updateThanhToan(@PathVariable("id") int id, @ModelAttribute("thanhToan") ThanhToan thanhToan) {
        ThanhToan existingThanhToan = thanhToanService.getThanhToanById(id);
        // Giữ nguyên giá trị ngày giờ của thanhToan gốc
        thanhToan.setPaymentDate(existingThanhToan.getPaymentDate());
        thanhToanService.updateThanhToan(thanhToan);
        return "redirect:/thanhtoan";
    }


    @GetMapping("/thanhtoan/delete/{id}")
    public String deleteThanhToan(@PathVariable("id") int id) {
        thanhToanService.deleteThanhToan(id);
        return "redirect:/thanhtoan";
    }
}
