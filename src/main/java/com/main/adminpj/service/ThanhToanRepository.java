package com.main.adminpj.service;

import com.main.adminpj.entity.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThanhToanRepository extends JpaRepository<ThanhToan, Integer> {
    // Các phương thức tùy chỉnh có thể được thêm ở đây nếu cần
}