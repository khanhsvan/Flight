package com.main.adminpj.service;

import com.main.adminpj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getUserDetails(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        List<User> users = jdbcTemplate.query(query, new Object[]{username}, new UserMapper());
        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }

    public void updateBalanceByUsername(String email, BigDecimal amount) {
        String sql = "UPDATE users SET balance = balance + ? WHERE email = ?";
        jdbcTemplate.update(sql, amount, email);
    }


    private static final class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setIdCard(rs.getString("id_card"));
            user.setName(rs.getString("name"));
            user.setRole(rs.getString("role"));
            return user;
        }
    }

    public BigDecimal getBalance(String username) {
        String query = "SELECT balance FROM users WHERE username = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{username}, BigDecimal.class);
    }


    public void updateBalance(String username, BigDecimal amount) {
        String sql = "UPDATE users SET balance = balance + ? WHERE username = ?";
        jdbcTemplate.update(sql, amount, username);
    }
}
