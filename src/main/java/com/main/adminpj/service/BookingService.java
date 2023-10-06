package com.main.adminpj.service;

import com.main.adminpj.model.BookingHistory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class BookingService {
    private final JdbcTemplate jdbcTemplate;

    public BookingService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<BookingHistory> getBookingHistory(String email) {
        String sql = "SELECT bh.ma_ve, bh.price, bh.so_ghe, bh.loai_ghe, tt.payment_status, tt.payment_date, f.flight_number, f.departure_date, f.origin_city, f.destination_city " +
                "FROM bookinglist bh " +
                "JOIN thanh_toan tt ON bh.ma_ve = tt.payment_id " +
                "JOIN flights f ON bh.flight_number = f.flight_number " +
                "WHERE bh.email = ?";
        return jdbcTemplate.query(sql, new Object[]{email}, new BookingHistoryRowMapper());
    }

}
