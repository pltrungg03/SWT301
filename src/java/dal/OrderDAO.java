/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Cart;
import model.Item;
import model.Order;
import model.OrderDetail;
import model.Products;

/**
 *
 * @author lactr
 */
public class OrderDAO extends DBContext {

    public void addOrder(Account a, Cart cart) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            String sql = "insert into [oder] value (?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, date);
            st.setInt(2, a.getId());
            st.setDouble(3, cart.getTotalMoney());
            st.executeUpdate();
            PreparedStatement st1 = connection.prepareStatement(sql);

            String sql1 = "select top 1 id from [Order] order by id desc";
            ResultSet rs = st1.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt(1);
                for (Item i : cart.getItems()) {
                    String sql2 = "insert into [orderline] value(?,?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql);
                    st2.setInt(1, oid);
                    st2.setInt(2, i.getProduct().getId());
                    st2.setInt(3, i.getQuantity());
                    st2.setDouble(4, i.getPrice());
                    st2.executeUpdate();
                }
            }
        } catch (Exception e) {
        }
    }

    public List<Order> getAllOrderByCustomer(int cusId) {
        String query = "select o.id,o.cid,o.date,o.totalmoney as [totalMoney]\n"
                + " from [Order] as o\n"
                + " where o.cid = ?";

        List<Order> list = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, cusId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AccountDAO daoL = new AccountDAO();

                Account customer = daoL.getDetail(rs.getInt("cid"));

                Order o = new Order(rs.getInt("id"),
                        customer,
                        rs.getString("date"),
                        rs.getDouble("totalMoney")
                );
                list.add(o);
            }
        } catch (SQLException e) {

        }

        return list;
    }

    public List<OrderDetail> getDetailOrder(int oId) {
        List<OrderDetail> list = new ArrayList<>();
        String query = "select ol.oId,ol.pId,ol.quantity,ol.price\n"
                + "from OrderLine as ol, Product as p\n"
                + "where ol.pId = p.id\n"
                + "and ol.oId = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, oId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DAO daoP = new DAO();
                Products p = daoP.getProductbyID(rs.getString("pId"));
                OrderDetail od = new OrderDetail(oId, p, rs.getInt("quantity"));
                list.add(od);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public List<OrderDetail> getDetailOrderByCustomer(int oId, int cusId) {
        List<OrderDetail> list = new ArrayList<>();
        String query = "select ol.oId,ol.pId,ol.quantity,ol.price\n"
                + "from OrderLine as ol, [Order] as o\n"
                + "where ol.oId = o.id\n"
                + "and o.cid = ?\n"
                + "and ol.oId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, cusId);
            ps.setInt(2, oId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DAO daoP = new DAO();
                Products p = daoP.getProductbyID(rs.getString("pId"));
                OrderDetail od = new OrderDetail(oId, p, rs.getInt("quantity"));
                list.add(od);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        List<OrderDetail> list = dao.getDetailOrderByCustomer(3, 32);
        for (OrderDetail order : list) {
            System.out.println(order);
        }
    }
}
