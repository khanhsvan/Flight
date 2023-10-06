package com.main.adminpj.service;

import com.main.adminpj.entity.flights;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminFlightRepository extends JpaRepository<flights, Integer> {
}
