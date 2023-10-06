package com.main.adminpj.model;

import java.math.BigDecimal;
import java.util.Date;

public class BookingHistory {
    private String maVe;
    private BigDecimal price;
    private String soGhe;
    private String loaiGhe;
    private String paymentStatus;
    private Date paymentDate;
    private String flightNumber;
    private Date departureDate;
    private String originCity;
    private String destinationCity;

    // Add constructors, getters, and setters

    public String getMaVe() {
        return maVe;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSoGhe() {
        return soGhe;
    }

    public String getLoaiGhe() {
        return loaiGhe;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public String getOriginCity() {
        return originCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSoGhe(String soGhe) {
        this.soGhe = soGhe;
    }

    public void setLoaiGhe(String loaiGhe) {
        this.loaiGhe = loaiGhe;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public BookingHistory() {
    }

    public BookingHistory(String maVe, BigDecimal price, String soGhe, String loaiGhe, String paymentStatus, Date paymentDate, String flightNumber, Date departureDate, String originCity, String destinationCity) {
        this.maVe = maVe;
        this.price = price;
        this.soGhe = soGhe;
        this.loaiGhe = loaiGhe;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.originCity = originCity;
        this.destinationCity = destinationCity;
    }

    // Add any additional methods as needed

    // Sample toString() method for debugging/logging
    @Override
    public String toString() {
        return "BookingHistory{" +
                "maVe='" + maVe + '\'' +
                ", price=" + price +
                ", soGhe='" + soGhe + '\'' +
                ", loaiGhe='" + loaiGhe + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paymentDate=" + paymentDate +
                ", flightNumber='" + flightNumber + '\'' +
                ", departureDate=" + departureDate +
                ", originCity='" + originCity + '\'' +
                ", destinationCity='" + destinationCity + '\'' +
                '}';
    }
}
