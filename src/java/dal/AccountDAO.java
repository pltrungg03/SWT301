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
import model.Products;

/**
 *
 * @author lactr
 */
public class AccountDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public Account loginAcc(String user, String pass) {
        String sql = "select * from Account\n"
                + "where [user] = ? \n"
                + "and pass = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            rs = st.executeQuery();

            while (rs.next()) {
                return new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5)
                );

            }
        } catch (Exception e) {
        }
        return null;
    }

    public Account checkAccountExist(String user) {
        String sql = "select * from Account\n"
                + "where [user] = ? \n";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            rs = st.executeQuery();

            while (rs.next()) {
                return new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5)
                );

            }
        } catch (Exception e) {
        }
        return null;
    }

    public void signUp(String user, String pass) {
        String sql = "insert into account\n"
                + "values\n"
                + "(?,?,0,0)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void addOrder(Account customer, Cart cart) {
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();
        try {
            // add order
            String query1 = "INSERT INTO [dbo].[Order]\n"
                    + "           (\n"
                    + "           [cid]\n"
                    + "           ,[date]\n"
                    + "           ,[totalmoney])\n"
                    + "     VALUES\n"
                    + "           (\n"
                    + "           ?,\n"
                    + "           ?,\n"
                    + "           ?)";
            PreparedStatement ps1 = connection.prepareStatement(query1);
            ps1.setInt(1, customer.getId());
            ps1.setString(2, date);
            ps1.setDouble(3, cart.getCartMoney());
            ps1.executeUpdate();

            // get OrderId 
            String query2 = "select top(1) o.id from [Order] as o\n"
                    + "order by o.id desc";
            PreparedStatement ps2 = connection.prepareStatement(query2);
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) {
                int oid = rs2.getInt("id");
                for (Item i : cart.getItems()) {
                    String query3 = "INSERT INTO [dbo].[OrderLine]\n"
                            + "           ([oId]\n"
                            + "           ,[pId]\n"
                            + "           ,[quantity]\n"
                            + "           ,[price])\n"
                            + "     VALUES\n"
                            + "           (?\n"
                            + "           ,?\n"
                            + "           ,?\n"
                            + "           ,?)";
                    PreparedStatement ps3 = connection.prepareStatement(query3);
                    ps3.setInt(1, oid);
                    ps3.setInt(2, i.getProduct().getId());
                    ps3.setInt(3, i.getQuantity());
                    ps3.setDouble(4, i.getPrice());
                    ps3.executeUpdate();
                }
            }
            // cap nhat lai so luong san pham
            String query4 = "UPDATE [dbo].[Product]\n"
                    + "   SET \n"
                    + "      [quantity] = ?\n"
                    + " WHERE id=?";
            PreparedStatement ps4 = connection.prepareStatement(query4);
            for (Item i : cart.getItems()) {
                ps4.setInt(1, i.getQuantity());
                ps4.setInt(2, i.getProduct().getId());
                ps4.executeUpdate();
            }
        } catch (Exception e) {
        }
    }

    public List<Account> getallAccount() {
        List<Account> listacc = new ArrayList<>();
        String sql = "select * from Account";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Account acc = new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5)
                );
                listacc.add(acc);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listacc;
    }

    public void setSeller(int id, int choice) {
        String query = "Update Account set isSell=?\n"
                + "where uID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, choice);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public Account getDetail(int id) {
        String query = "select * from Account\n"
                + "where uID=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
             rs = ps.executeQuery();
            if (rs.next()) {
                Account acc = new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
                
                return acc;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public static void main(String[] args) {
        AccountDAO adao = new AccountDAO();
        List<Account> list = adao.getallAccount();
        System.out.println(adao.getDetail(1));
    }
}
