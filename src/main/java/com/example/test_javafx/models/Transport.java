package com.example.test_javafx.models;

public class Transport {
    private String id;
    private String name;
    private double value_day;
    private double h_required;
    private double expense;
    private double tra_month;
    private int num_att;

    public Transport(String id, String name, double valueDay, double hRequired, double expense, double traMonth, int numAtt) {
        this.id = id;
        this.name = name;
        value_day = valueDay;
        h_required = hRequired;
        this.expense = expense;
        tra_month = traMonth;
        num_att = numAtt;
    }

    public double getH_required() {
        return h_required;
    }

    public void setH_required(double h_required) {
        this.h_required = h_required;
    }

    public double getValue_day() {
        return value_day;
    }

    public void setValue_day(double value_day) {
        this.value_day = value_day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public double getTra_month() {
        return tra_month;
    }

    public void setTra_month(double tra_month) {
        this.tra_month = tra_month;
    }

    public int getNum_att() {
        return num_att;
    }

    public void setNum_att(int num_att) {
        this.num_att = num_att;
    }
}