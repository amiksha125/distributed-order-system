package dao;

import config.DBConnection;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductDAO {

    public void addProduct(Product product) {

        String sql = "INSERT INTO products(name, price) VALUES (?, ?)";

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());

            pstmt.executeUpdate();

            System.out.println("Product inserted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}