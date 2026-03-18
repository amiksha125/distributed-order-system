
package dao;

import config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InventoryDAO {

    public void addInventory(int productId, int quantity) {

        String sql = "INSERT INTO inventory(product_id, quantity) VALUES (?, ?) "
                + "ON DUPLICATE KEY UPDATE quantity = quantity + ?";

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, productId);
            pstmt.setInt(2, quantity);
            pstmt.setInt(3, quantity);

            pstmt.executeUpdate();

            System.out.println("Inventory added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getProductStock(int productId) {

        String sql = "SELECT quantity FROM inventory WHERE product_id = ?";

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, productId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                int quantity = rs.getInt("quantity");

                System.out.println("Stock for product " + productId + ": " + quantity);

            } else {

                System.out.println("Product not found in inventory");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
