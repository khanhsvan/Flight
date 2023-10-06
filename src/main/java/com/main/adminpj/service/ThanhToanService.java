package com.main.adminpj.service;

import com.main.adminpj.entity.ThanhToan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThanhToanService {

    @Autowired
    private ThanhToanRepository thanhToanRepository;

    public List<ThanhToan> getAllThanhToan() {
        return thanhToanRepository.findAll();
    }

    public ThanhToan getThanhToanById(int id) {
        return thanhToanRepository.findById(id).orElse(null);
    }

    public void updateThanhToan(ThanhToan thanhToan) {
        thanhToanRepository.save(thanhToan);
    }

    public void deleteThanhToan(int id) {
        thanhToanRepository.deleteById(id);
    }
}
