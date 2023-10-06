package com.main.adminpj.dao;

import com.main.adminpj.entity.SeatRowMapper;
import com.main.adminpj.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeatDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addSeat(Seat seat) {
        String query = "INSERT INTO seats (seat_number, class_type, reserved) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, seat.getSeatNumber(), seat.getClassType(), seat.isReserved());
    }

    // Sửa Seat
    public void updateSeat(Seat seat) {
        String query = "UPDATE seats SET seat_number = ?, class_type = ?, reserved = ? WHERE id = ?";
        jdbcTemplate.update(query, seat.getSeatNumber(), seat.getClassType(), seat.isReserved(), seat.getId());
    }

    // Xóa Seat
    public void deleteSeat(int id) {
        String query = "DELETE FROM seats WHERE id = ?";
        jdbcTemplate.update(query, id);
    }


    public List<Seat> getSeatsByFlightNumber(String flightNumber) {
        int seatCount = getSeatCountByFlightNumber(flightNumber); // Lấy số lượng ghế từ bảng flights

        String sql = "SELECT * FROM seats WHERE reserved = false AND seat_number <= ? ORDER BY class_type DESC, seat_number ASC";
        return jdbcTemplate.query(sql, new SeatRowMapper(), seatCount);
    }

    public int getSeatCountByFlightNumber(String flightNumber) {
        String sql = "SELECT number_seat FROM flights WHERE flight_number = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, flightNumber);
    }


    public int getReservedSeatCountByFlightNumber(String flightNumber) {
        String sql = "SELECT COUNT(*) FROM seats WHERE reserved=true";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public Seat getSeatById(int seatId) {
        String sql = "SELECT * FROM seats WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new SeatRowMapper(), seatId);
    }

    public Seat getSeatNumberBySeatId(int seatId){
        String sql = "SELECT seat_number FROM seats WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new SeatRowMapper(), seatId);

    }

    public List<Seat> getAllSeat(){
        String sql = " select * from seats";
        return jdbcTemplate.query(sql, new SeatRowMapper());

    }

    public void reserveSeat(String seatNumber) {
        String sql = "UPDATE seats SET reserved = true WHERE seat_number = ?";
        jdbcTemplate.update(sql, seatNumber);
    }

    public String getClasstypeBySeatNumber(String seatNumber) {
        String sql = "SELECT class_type FROM seats WHERE seat_number = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{seatNumber}, String.class);
    }

    public double calculateFare(String classType) {
        double fare = 0.0;
        switch (classType) {
            case "Economy":
                fare = 100.0;
                break;
            case "Business":
                fare = 200.0;
                break;
            // Add cases for other seat classes if applicable
        }
        return fare;
    }


}
