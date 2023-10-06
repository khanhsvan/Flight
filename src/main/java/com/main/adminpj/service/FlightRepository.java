package com.main.adminpj.service;

import com.main.adminpj.entity.flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<flights, Long> {

    @Query("SELECT DISTINCT f FROM flights f " +
            "WHERE (:flightName IS NULL OR f.flightName = :flightName OR :flightName = '') " +
            "AND (:flightNumber IS NULL OR f.flightNumber = :flightNumber OR :flightNumber = '') " +
            "AND (:departureTime IS NULL OR f.departureTime = :departureTime) " +
            "AND (:arrivalTime IS NULL OR f.arrivalTime = :arrivalTime) " +
            "AND (:departureDate IS NULL OR f.departureDate = :departureDate) " +
            "AND (:arrivalDate IS NULL OR f.arrivalDate = :arrivalDate) " +
            "AND (:originCity IS NULL OR f.originCity = :originCity OR :originCity = '') " +
            "AND (:arrivalCity IS NULL OR f.destinationCity = :arrivalCity OR :arrivalCity = '')")
    List<flights> searchFlights(@Param("flightName") String flightName,
                                @Param("flightNumber") String flightNumber,
                                @Param("departureTime") LocalTime departureTime,
                                @Param("arrivalTime") LocalTime arrivalTime,
                                @Param("departureDate") LocalDate departureDate,
                                @Param("arrivalDate") LocalDate arrivalDate,
                                @Param("originCity") String originCity,
                                @Param("arrivalCity") String arrivalCity);


}
