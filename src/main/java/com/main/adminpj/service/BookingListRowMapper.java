package com.main.adminpj.service;

import com.main.adminpj.model.bookinglist;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingListRowMapper implements RowMapper<bookinglist> {
    @Override
    public bookinglist mapRow(ResultSet rs, int rowNum) throws SQLException {
        bookinglist booking = new bookinglist();
        booking.setId(rs.getInt("id"));
        booking.setSo_dien_thoai(rs.getString("so_dien_thoai"));
        booking.setEmail(rs.getString("email"));
        booking.setHo_ten(rs.getString("ho_ten"));
        booking.setId_card(rs.getString("id_card"));
        booking.setFlight_number(rs.getString("flight_number"));
        booking.setLoai_ghe(rs.getString("loai_ghe"));
        booking.setMa_ve(rs.getString("ma_ve"));
        booking.setPrice(rs.getDouble("price"));
        booking.setImgLink(rs.getString("ImgLink"));
        booking.setSo_ghe(rs.getString("so_ghe"));
        return booking;
    }
}
