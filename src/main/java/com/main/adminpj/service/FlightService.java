package com.main.adminpj.service;

import com.main.adminpj.entity.flights;
import com.main.adminpj.model.flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class FlightService {

    public  final JdbcTemplate jdbcTemplate;

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(JdbcTemplate jdbcTemplate, FlightRepository flightRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.flightRepository = flightRepository;
    }

    public List<flights> getAllFlights() {
        return flightRepository.findAll();
    }

    public List<flights> searchFlights(String flightName, String flightNumber, LocalTime departureTime, LocalTime arrivalTime,
                                       LocalDate departureDate, LocalDate arrivalDate, String originCity, String destinationCity) {
        return flightRepository.searchFlights(flightName, flightNumber, departureTime, arrivalTime,
                departureDate, arrivalDate, originCity, destinationCity);
    }

    public List<flight> getAllFlight() {
        String sql = "SELECT * FROM flights";
        return jdbcTemplate.query(sql, new FlightRowMapper());
    }



}



