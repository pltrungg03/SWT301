/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Category;
import model.Products;

/**
 *
 * @author lactr
 */
public class DAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public List<Products> getAll() {
        List<Products> list = new ArrayList<>();
        String sql = "select * from product";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Products pd = new Products(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title")
                );
                pd.setQuantity(rs.getInt("quantity"));
                list.add(pd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Category> getAllCate() {
        List<Category> list = new ArrayList<>();

        String sql = "select * from Category";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Category cr = new Category(
                        rs.getInt("cID"),
                        rs.getString("cName")
                );
                list.add(cr);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Products getLast() {
        String sql = "select top 1 * from product\n"
                + "order by id desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Products pd = new Products(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title")
                );
                return pd;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public List<Products> getProductsByCate(String cateID) {
        List<Products> list = new ArrayList<>();
        String sql = "select * from product \n"
                + "where cateID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cateID);
            rs = st.executeQuery();
            while (rs.next()) {
                Products pd = new Products(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title")
                );
                list.add(pd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Products getProductbyID(String id) {
        String sql = "select * from product\n"
                + "where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                Products pd = new Products(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title")
                );
                pd.setQuantity(rs.getInt("quantity"));
                return pd;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public List<Products> searchProductsbyTitle(String title) {
        List<Products> list = new ArrayList<>();
        String sql = "select * from product\n"
                + "where title like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + title + "%");
            rs = st.executeQuery();
            while (rs.next()) {
                Products pd = new Products(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title")
                );
                list.add(pd);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public List<Products> getProductsByisSell(int isSell) {
        List<Products> list = new ArrayList<>();
        String sql = "select * from product\n"
                + "where sell_ID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, isSell);
            rs = st.executeQuery();
            while (rs.next()) {
                Products pd = new Products(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title")
                );
                list.add(pd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void deleteProduct(String id) {
        String sql = "delete from product\n"
                + "where id=? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertProduct(String name, String image, String price, String title, int category, int sid) {
        String sql = "INSERT [dbo].[product] \n"
                + "([name], [image], [price], [title],[cateID], [sell_ID]) \n"
                + "VALUES(?,?,?,?,?,?)\n";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, image);
            st.setString(3, price);
            st.setString(4, title);
            st.setInt(5, category);
            st.setInt(6, sid);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void editProduct(String name, String image, String price, String title, String category, String pid) {
        String sql = "UPDATE [dbo].[product]\n"
                + "   SET [name] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[title] = ?\n"
                + "      ,[cateID] = ?\n"
                + " WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, image);
            st.setString(3, price);
            st.setString(4, title);
            st.setString(5, category);
            st.setString(6, pid);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

}
