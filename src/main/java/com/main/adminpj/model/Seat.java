package com.main.adminpj.model;

public class Seat {

    private int id;
    private String seatNumber;
    private String classType;
    private boolean reserved;

    public Seat() {
    }

    public Seat(int id, String seatNumber, String classType, boolean reserved) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.classType = classType;
        this.reserved = reserved;
    }

    // getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return "Seat [id=" + id + ", seatNumber=" + seatNumber + ", classType=" + classType + ", reserved=" + reserved
                + "]";
    }
}
