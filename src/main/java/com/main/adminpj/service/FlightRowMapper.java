package com.main.adminpj.service;

import com.main.adminpj.model.flight;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class FlightRowMapper implements RowMapper<flight> {

    @Override
    public flight mapRow(ResultSet rs, int rowNum) throws SQLException {
        flight flight = new flight();
        flight.setId(rs.getInt("id"));
        flight.setFlight_name(rs.getString("flight_name"));
        flight.setFlight_number(rs.getString("flight_number"));
        flight.setDeparture_date(Date.valueOf(rs.getString("departure_date")));
        flight.setDeparture_time(Time.valueOf(rs.getString("departure_time")));
        flight.setArrival_date(Date.valueOf(rs.getString("arrival_date")));
        flight.setArrival_time(Time.valueOf(rs.getString("arrival_time")));
        flight.setOrigin_city(rs.getString("origin_city"));
        flight.setDestination_city(rs.getString("destination_city"));
        flight.setNumber_seat(rs.getInt("number_seat"));
        return flight;
    }
}
