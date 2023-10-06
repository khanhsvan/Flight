package com.main.adminpj.controller;

import com.main.adminpj.model.User;
import com.main.adminpj.model.payment_system;
import com.main.adminpj.service.PaymentService;
import com.main.adminpj.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class PaymentController {
    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment")
    public String paymentPage(Model model, HttpSession session) {
        User userC = getUserFromSession(session, model);

        if (userC == null) {
            model.addAttribute("error", "Vui lòng đăng nhập trước khi tiếp tục");
            return "login";
        }

        String username = userC.getUsername();
        User user = userService.getUserDetails(username);
        BigDecimal balance = userService.getBalance(username);
        List<payment_system> paymentList = paymentService.getAllPayments();

        model.addAttribute("user", user);
        model.addAttribute("balance", balance);
        model.addAttribute("paymentList", paymentList);

        return "payment";
    }

    @PostMapping("/payment/update")
    public String updateBalance(@RequestParam("username") String username,
                                @RequestParam("amount") BigDecimal amount,
                                @RequestParam(value = "externalCardNumber", required = false) String externalCardNumber,
                                @RequestParam(value = "externalCCV", required = false) String externalCCV,
                                @RequestParam(value = "externalExpirationDate", required = false) String externalExpirationDate,
                                @RequestParam(value = "availableCard", required = false) Long availableCard) {
        if (externalCardNumber != null && !externalCardNumber.isEmpty() &&
                externalCCV != null && !externalCCV.isEmpty() &&
                externalExpirationDate != null && !externalExpirationDate.isEmpty()) {
            // Thực hiện thêm thông tin thẻ bên ngoài vào cơ sở dữ liệu
            paymentService.addPayment(username, externalCardNumber, externalCCV, externalExpirationDate);
        } else if (availableCard != null) {
            // Thực hiện cập nhật số dư tài khoản với thẻ có sẵn
            payment_system selectedPayment = paymentService.getPaymentById(availableCard);
            if (selectedPayment != null) {
                userService.updateBalance(username, amount);
            }
        }

        return "redirect:/payment";
    }


    private User getUserFromSession(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            model.addAttribute("error", "Vui lòng đăng nhập trước khi tiếp tục");
            return null;
        }

        User userDetails = userService.getUserDetails(user.getUsername());

        if (userDetails == null) {
            model.addAttribute("error", "Không tìm thấy thông tin người dùng. Vui lòng liên hệ trung tâm trợ giúp");
            return null;
        }

        model.addAttribute("user", userDetails);

        return userDetails;
    }
}
