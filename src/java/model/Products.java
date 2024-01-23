/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author lactr
 */
public class Products {
    private int id;
    private String name;
    private String image;
    private double price;
    private String title;
    private int quantity;
    public Products(int id, String name, String image, double price, String title) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.title = title;
 }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

     
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Products{" + "id=" + id + ", name=" + name + ", image=" + image + ", price=" + price + ", title=" + title + '}';
    }

    
}
