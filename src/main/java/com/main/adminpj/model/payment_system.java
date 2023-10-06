package com.main.adminpj.model;


public class payment_system {
    private int id;
    private String name;
    private String description;
    private String payment_method;
    private String bank_account;

    private String username;

    public payment_system() {}

    public String getPayment_method() {return payment_method;}

    public void setPayment_method(String payment_method) {this.payment_method = payment_method;}

    public String getBank_account() {return bank_account;}

    public void setBank_account(String bank_account) {this.bank_account = bank_account;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentMethod() {
        return payment_method;
    }

    public void setPaymentMethod(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getBankAccount() {
        return bank_account;
    }

    public void setBankAccount(String bank_account) {
        this.bank_account = bank_account;
    }

    public payment_system(int id, String name, String description, String payment_method, String bank_account) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.payment_method = payment_method;
        this.bank_account = bank_account;
    }

    public payment_system(int id, String name, String description, String payment_method, String bank_account, String username) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.payment_method = payment_method;
        this.bank_account = bank_account;
        this.username = username;
    }
}

