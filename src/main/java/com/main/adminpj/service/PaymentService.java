package com.main.adminpj.service;

import com.main.adminpj.entity.ThanhToan;
import com.main.adminpj.model.payment_system;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PaymentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<payment_system> getAllPayments() {
        String sql = "SELECT * FROM payment_system";
        return jdbcTemplate.query(sql, new PaymentRowMapper());
    }

    public void addPayment(String username, String cardNumber, String cardType, String expirationDate) {
        String sql = "INSERT INTO payment_system (name, description, payment_method, bank_account, username) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, cardType, "", cardType, cardNumber, username);
    }

    public payment_system getPaymentById(Long paymentId) {
        String sql = "SELECT * FROM payment_system WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{paymentId}, new BeanPropertyRowMapper<>(payment_system.class));
    }


    public void addPayment(ThanhToan thanhToan) {
        String sql = "INSERT INTO thanh_toan (payment_id, payment_status, payment_amount, payment_currency, payment_date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, thanhToan.getPaymentId(), thanhToan.getPaymentStatus(), thanhToan.getPaymentAmount(), thanhToan.getPaymentCurrency(), thanhToan.getPaymentDate());
    }

}
