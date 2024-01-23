/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.OrderDetail;

/**
 *
 * @author lactr
 */
public class ViewDetailCustomer extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String cusId_raw = request.getParameter("cId");
        String orderId_raw = request.getParameter("oId");
        try {
            int cusId = Integer.parseInt(cusId_raw);
            int orderId = Integer.parseInt(orderId_raw);
            OrderDAO dao = new OrderDAO();
            List<OrderDetail> list = dao.getDetailOrderByCustomer(orderId, cusId);
            PrintWriter out = response.getWriter();
            for (OrderDetail o : list) {
                out.print("<tr>\n"
                        + "<td class=\"align-middle\" style=\"width: 100px;\"><img src=\"" + o.getP().getImage() + "\" alt=\"\" style=\"width: 100%;\"></td>"
                        + "                                <td>" + o.getP().getName() + "</td>\n"
                        + "                                <td>" + o.getQuantity() + "</td>\n"
                        + "                                <td>" + o.getP().getPrice() + "</td>\n"
                        + "                            </tr>");
            }
        } catch (NumberFormatException e) {

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
