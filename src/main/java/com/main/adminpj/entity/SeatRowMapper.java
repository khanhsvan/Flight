package com.main.adminpj.entity;

import com.main.adminpj.model.Seat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatRowMapper implements RowMapper<Seat> {

    @Override
    public Seat mapRow(ResultSet rs, int rowNum) throws SQLException {
        Seat seat = new Seat();
        seat.setId(rs.getInt("id"));
        seat.setSeatNumber(rs.getString("seat_number"));
        seat.setClassType(rs.getString("class_type"));
        seat.setReserved(rs.getBoolean("reserved"));
        return seat;
    }
}
