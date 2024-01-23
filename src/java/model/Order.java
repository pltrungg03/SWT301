/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author lactr
 */
public class Order {

    private int id;
    private Account c;
    private String date;

    private double totalmoney;

    public Order() {
    }

    public Order(int id, Account c, String date, double totalmoney) {
        this.id = id;
        this.c = c;
        this.date = date;
        this.totalmoney = totalmoney;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Account getC() {
        return c;
    }

    public void setC(Account c) {
        this.c = c;
    }



    public double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(double totalmoney) {
        this.totalmoney = totalmoney;
    }

}
