/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DAO;
import java.util.ArrayList;
import java.util.List;


public class Cart {

    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    private Item getItemById(int pid ) {

        for (Item i : items) {
            if (i.getProduct().getId() == pid) {
                return i;
            }
        }

        return null;
    }
    
    public int getQuantityById(int id) {
        return getItemById(id).getQuantity();
    }

    public void addItem(Item i) {
        if (getItemById(i.getProduct().getId()) != null) {
            Item oldItem = getItemById(i.getProduct().getId());
            oldItem.setQuantity(oldItem.getQuantity() + i.getQuantity());
        } else {
            items.add(i);
        }
    }

    public void removeItem(int id) {
        if (getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }

    public double getTotalMoney() {
        double total = 0;

        for (Item i : items) {
            total += i.getQuantity() * i.getPrice();
        }

        return total;
    }
    public double getCartMoney(){
        return getTotalMoney()*1.1;
    }

    private  Products getProductById(int pId, List<Products> list) {
        for (Products x : list) {
            if (x.getId() == pId) {
                return x;
            }
        }
        return null;
    }
    
    public Cart(String txt, List<Products> list) {
        items = new ArrayList<>();
        try {
            if (txt != null && txt.length() != 0) {
                String[] listP = txt.split("/");
                for (String p : listP) {
                    String[] info = p.split(":");
                    int pId = Integer.parseInt(info[0]);
                    int quantity = Integer.parseInt(info[1]);
                    Products product = getProductById(pId,list);
                    Item i = new Item(product, quantity, product.getPrice());
                    addItem(i);
                }
            }
        } catch (NumberFormatException e){
            
        }

    }
    public static void main(String[] args) {
        String ab = "1:1/1:1/1:1/1:1/1:1/2:1/2:1/3:1/2:1/1:1/1:1";

        DAO s = new DAO();
        Cart c = new Cart(ab, s.getAll());
        for (Item i : c.items) {
            System.out.println(i.getProduct().getName());
            System.out.println(i.getQuantity());
        }
        
                
    }
}
