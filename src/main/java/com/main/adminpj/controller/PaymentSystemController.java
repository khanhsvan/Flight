package com.main.adminpj.controller;

import com.main.adminpj.model.payment_system;
import com.main.adminpj.dao.PaymentSystemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PaymentSystemController {

    @Autowired
    private PaymentSystemDAO paymentSystemDAO;

    @GetMapping("/payments")
    public String viewPayments(Model model) {
        List<payment_system> payments = paymentSystemDAO.getAllPaymentSystems();
        model.addAttribute("payments", payments);
        return "admin/payments";
    }

    @GetMapping("/payments/add")
    public String showAddPaymentSystemForm(Model model) {
        model.addAttribute("paymentSystem", new payment_system());
        return "admin/addPaymentSystem";
    }

    @PostMapping("/payments/add")
    public String addPaymentSystem(@ModelAttribute("paymentSystem") payment_system paymentSystem) {
        paymentSystemDAO.addPaymentSystem(paymentSystem);
        return "redirect:/payments";
    }

    @GetMapping("/payments/edit/{id}")
    public String showEditPaymentSystemForm(@PathVariable("id") int id, Model model) {
        payment_system paymentSystem = paymentSystemDAO.getPaymentSystemById(id);
        model.addAttribute("paymentSystem", paymentSystem);
        return "admin/editPaymentSystem";
    }

    @PostMapping("/payments/update/{id}")
    public String updatePaymentSystem(@PathVariable("id") int id, @ModelAttribute("paymentSystem") payment_system paymentSystem) {
        paymentSystem.setId(id);
        paymentSystemDAO.updatePaymentSystem(paymentSystem);
        return "redirect:/payments";
    }

    @GetMapping("/payments/delete/{id}")
    public String deletePaymentSystem(@PathVariable("id") int id) {
        paymentSystemDAO.deletePaymentSystem(id);
        return "redirect:/payments";
    }
}
