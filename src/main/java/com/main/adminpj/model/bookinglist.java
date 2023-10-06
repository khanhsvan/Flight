package com.main.adminpj.model;

public class bookinglist {
    private int id;
    private String so_dien_thoai;
    private String email;
    private String ho_ten;
    private String id_card;
    private String flight_number;
    private String loai_ghe;
    private String ma_ve;
    private Double price;
    private String ImgLink;

    private String so_ghe;
    //getter setter


    public String getSo_ghe() {return so_ghe;}

    public void setSo_ghe(String so_ghe) {this.so_ghe = so_ghe;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    public String getLoai_ghe() {
        return loai_ghe;
    }

    public void setLoai_ghe(String loai_ghe) {
        this.loai_ghe = loai_ghe;
    }

    public String getMa_ve() {
        return ma_ve;
    }

    public void setMa_ve(String ma_ve) {
        this.ma_ve = ma_ve;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgLink() {
        return ImgLink;
    }

    public void setImgLink(String imgLink) {
        ImgLink = imgLink;
    }
    // constructor


    public bookinglist(int id, String so_dien_thoai, String email, String ho_ten, String id_card, String flight_number, String loai_ghe, String ma_ve, Double price, String imgLink) {
        this.id = id;
        this.so_dien_thoai = so_dien_thoai;
        this.email = email;
        this.ho_ten = ho_ten;
        this.id_card = id_card;
        this.flight_number = flight_number;
        this.loai_ghe = loai_ghe;
        this.ma_ve = ma_ve;
        this.price = price;
        ImgLink = imgLink;
    }

    public bookinglist() {
    }

    public bookinglist(int id, String so_dien_thoai, String email, String ho_ten, String id_card, String flight_number, String loai_ghe, String ma_ve, Double price, String imgLink, String so_ghe) {
        this.id = id;
        this.so_dien_thoai = so_dien_thoai;
        this.email = email;
        this.ho_ten = ho_ten;
        this.id_card = id_card;
        this.flight_number = flight_number;
        this.loai_ghe = loai_ghe;
        this.ma_ve = ma_ve;
        this.price = price;
        ImgLink = imgLink;
        this.so_ghe = so_ghe;
    }
}
