package com.main.adminpj.service;

import com.main.adminpj.model.payment_system;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRowMapper implements RowMapper<payment_system> {
    @Override
    public payment_system mapRow(ResultSet rs, int rowNum) throws SQLException {
        payment_system payment = new payment_system();
        payment.setId(rs.getInt("id"));
        payment.setName(rs.getString("name"));
        payment.setDescription(rs.getString("description"));
        payment.setPaymentMethod(rs.getString("payment_method"));
        payment.setBankAccount(rs.getString("bank_account"));
        payment.setUsername(rs.getString("username"));
        return payment;
    }
}
