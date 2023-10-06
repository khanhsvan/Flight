package com.main.adminpj.dao;

import com.main.adminpj.model.payment_system;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentSystemDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<payment_system> getAllPaymentSystems() {
        String query = "SELECT * FROM payment_system";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(payment_system.class));
    }

    public payment_system getPaymentSystemById(int id) {
        String query = "SELECT * FROM payment_system WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper<>(payment_system.class));
    }

    public void addPaymentSystem(payment_system paymentSystem) {
        String query = "INSERT INTO payment_system (name, description, payment_method, bank_account, username) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, paymentSystem.getName(), paymentSystem.getDescription(),
                paymentSystem.getPaymentMethod(), paymentSystem.getBankAccount(), paymentSystem.getUsername());
    }

    public void updatePaymentSystem(payment_system paymentSystem) {
        String query = "UPDATE payment_system SET name = ?, description = ?, payment_method = ?, bank_account = ?, username = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(query, paymentSystem.getName(), paymentSystem.getDescription(),
                paymentSystem.getPaymentMethod(), paymentSystem.getBankAccount(), paymentSystem.getUsername(),
                paymentSystem.getId());
    }

    public void deletePaymentSystem(int id) {
        String query = "DELETE FROM payment_system WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
