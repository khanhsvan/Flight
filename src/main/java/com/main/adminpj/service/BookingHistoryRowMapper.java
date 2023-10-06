package com.main.adminpj.service;

import com.main.adminpj.model.BookingHistory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingHistoryRowMapper implements RowMapper<BookingHistory> {

    @Override
    public BookingHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookingHistory bookingHistory = new BookingHistory();
        bookingHistory.setMaVe(rs.getString("ma_ve"));
        bookingHistory.setPrice(rs.getBigDecimal("price"));
        bookingHistory.setSoGhe(rs.getString("so_ghe"));
        bookingHistory.setLoaiGhe(rs.getString("loai_ghe"));
        bookingHistory.setPaymentStatus(rs.getString("payment_status"));
        bookingHistory.setPaymentDate(rs.getDate("payment_date"));
        bookingHistory.setFlightNumber(rs.getString("flight_number"));
        bookingHistory.setDepartureDate(rs.getDate("departure_date"));
        bookingHistory.setOriginCity(rs.getString("origin_city"));
        bookingHistory.setDestinationCity(rs.getString("destination_city"));
        return bookingHistory;
    }
}
