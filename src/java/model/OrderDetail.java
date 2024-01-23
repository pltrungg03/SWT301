/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author lactr
 */
public class OrderDetail {
   private int oid;
   private Products p;
   private int quantity;
 

    public OrderDetail() {
    }

    public OrderDetail(int oid, Products p, int quantity) {
        this.oid = oid;
        this.p = p;
        this.quantity = quantity;
    }


    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public Products getP() {
        return p;
    }

    public void setP(Products p) {
        this.p = p;
    }


    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

   
}
