package com.main.adminpj.model;

public class FlightNSeat {
    private String flightNumber;
    private String Seat_number;
    //contructor getter setter


    public FlightNSeat() {
    }

    public FlightNSeat(String flightNumber, String seat_number) {
        this.flightNumber = flightNumber;
        Seat_number = seat_number;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getSeat_number() {
        return Seat_number;
    }

    public void setSeat_number(String seat_number) {
        Seat_number = seat_number;
    }


}
