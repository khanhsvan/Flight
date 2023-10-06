package com.main.adminpj.model;

import java.sql.Date;
import java.sql.Time;

public class flight {
    private int id;
    private String flight_name;
    private String flight_number;
    private Date departure_date;
    private Date arrival_date;
    private Time departure_time;
    private Time arrival_time;
    private String seat_class;
    private int number_seat;
    private String origin_city;
    private String destination_city;
    // getter and setter


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlight_name() {
        return flight_name;
    }

    public void setFlight_name(String flight_name) {
        this.flight_name = flight_name;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    public Date getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Date departure_date) {
        this.departure_date = departure_date;
    }

    public Date getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(Date arrival_date) {
        this.arrival_date = arrival_date;
    }

    public Time getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Time departure_time) {
        this.departure_time = departure_time;
    }

    public Time getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(Time arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getSeat_class() {
        return seat_class;
    }

    public void setSeat_class(String seat_class) {
        this.seat_class = seat_class;
    }

    public int getNumber_seat() {
        return number_seat;
    }

    public void setNumber_seat(int number_seat) {
        this.number_seat = number_seat;
    }

    public String getOrigin_city() {
        return origin_city;
    }

    public void setOrigin_city(String origin_city) {
        this.origin_city = origin_city;
    }

    public String getDestination_city() {
        return destination_city;
    }

    public void setDestination_city(String destination_city) {
        this.destination_city = destination_city;
    }

    //constructor



    public flight() {
    }

    public flight(int id, String flight_name, String flight_number, Date departure_date, Date arrival_date, Time departure_time, Time arrival_time, String seat_class, int number_seat, String origin_city, String destination_city) {
        this.id = id;
        this.flight_name = flight_name;
        this.flight_number = flight_number;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
        this.seat_class = seat_class;
        this.number_seat = number_seat;
        this.origin_city = origin_city;
        this.destination_city = destination_city;
    }
}
