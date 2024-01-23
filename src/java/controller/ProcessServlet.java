/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Cart;
import model.Category;
import model.Item;
import model.Products;

/**
 *
 * @author lactr
 */
public class ProcessServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProcessServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DAO daoP = new DAO();
        List<Products> listP = daoP.getAll();
        Cookie[] arr = request.getCookies();
        String txtCart = "";
        if (arr != null) {
            for (Cookie c : arr) {
                if (c.getName().equals("cart")) {
                    txtCart += c.getValue();
                    c.setValue("");
                    c.setPath("/");
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }
        System.out.println(txtCart + "|||||||||||||");
        Cart cart = new Cart(txtCart, listP);

        String num_raw = request.getParameter("num");
        String pId_raw = request.getParameter("id");

        int pId, num = 0;
        try {
            pId=Integer.parseInt(pId_raw);
         
         
            Products p = daoP.getProductbyID(pId_raw);
            System.out.println(p.getName());
            int numStore = p.getQuantity();
            num = Integer.parseInt(num_raw);
            if (num == -1 && (cart.getQuantityById(pId) <= 1)) {
                cart.removeItem(pId);
            } else {
                if (num == 1 && cart.getQuantityById(pId) >= numStore) {
                    num = 0;
                }
                double price = p.getPrice();
                Item t = new Item(p, num, price);
                System.out.println(num);
                cart.addItem(t);
            }
        } catch (Exception e) {

        }
        List<Item> items = cart.getItems();
        for (Item i : items) {
            System.out.println(i.getProduct().getName() + "|" + i.getQuantity() + "___________________");
        }
        txtCart = null;
        if (items.size() > 0) {
            txtCart = items.get(0).getProduct().getId() + ":"
                    + items.get(0).getQuantity();
            for (int i = 1; i < items.size(); i++) {
                txtCart += "/"
                        + items.get(i).getProduct().getId() + ":"
                        + items.get(i).getQuantity();
            }
        }
        System.out.println(txtCart + "--------------------");
        if (arr != null) {
            for (Cookie c : arr) {
                if (c.getName().equals("cart")) {
                    c = new Cookie("cart", txtCart);
                    c.setMaxAge(2 * 24 * 60 * 60);
                    response.addCookie(c);
                }
            }
        }
        request.setAttribute("cart", cart);
 
        Cart c = new Cart(txtCart, listP);
        List<Item> listI = c.getItems();
        int numberOfCart;
        if (!listI.isEmpty()) {
            numberOfCart = listI.size();
        } else {
            numberOfCart = 0;
        }
     
        request.setAttribute("numberOfCart", numberOfCart);
        request.getRequestDispatcher("cart.jsp").forward(request, response);

    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DAO daoP = new DAO();
        List<Products> listP = daoP.getAll();
        Cookie[] arr = request.getCookies();
        String txtCart = "";
        if (arr != null) {
            for (Cookie c : arr) {
                if (c.getName().equals("cart")) {
                    txtCart += c.getValue();
                    c.setValue("");
                    c.setPath("/");
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }
        Cart cart = new Cart(txtCart, listP);

        String pId_raw = request.getParameter("id");


        int pId;
        try {
            pId=Integer.parseInt(pId_raw);
         
            Products p = daoP.getProductbyID(pId_raw);
            System.out.println(p.getName());
            
            cart.removeItem(pId);
            
        } catch (Exception e) {

        }
        List<Item> items = cart.getItems();
        
        txtCart = null;
        if (items.size() > 0) {
            txtCart = items.get(0).getProduct().getId() + ":"
    
                    + items.get(0).getQuantity();
            for (int i = 1; i < items.size(); i++) {
                txtCart += "/"
                        + items.get(i).getProduct().getId() + ":"
               
                        + items.get(i).getQuantity();
            }
        }
        System.out.println(txtCart + "--------------------");
        if (arr != null) {
            for (Cookie c : arr) {
                if (c.getName().equals("cart")) {
                    c = new Cookie("cart", txtCart);
                    c.setMaxAge(2 * 24 * 60 * 60);
                    response.addCookie(c);
                }
            }
        }
        request.setAttribute("cart", cart);
      
       
        Cart c = new Cart(txtCart, listP);
        List<Item> listI = c.getItems();
        int numberOfCart;
        if (!listI.isEmpty()) {
            numberOfCart = listI.size();
        } else {
            numberOfCart = 0;
        }
      
        request.setAttribute("numberOfCart", numberOfCart);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
