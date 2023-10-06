package com.main.adminpj.model;

public class User {
    private String username;
    private String password;
    private String role;
    private int id;
    private String email;
    private String idCard;
    private String name;



    private double balance;
    // constructor
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, String role, int id, String email, String idCard, String name, double balance) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.id = id;
        this.email = email;
        this.idCard = idCard;
        this.name = name;
        this.balance =balance;
    }

    public User(String username, String password, String role, String email, String idCard, String name, double balance) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.idCard = idCard;
        this.name = name;
        this.balance = balance;
    }


    public User() {

    }

    // getters and setters
    public double getBalance() {return balance;}

    public void setBalance(double balance) {this.balance = balance;}
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
