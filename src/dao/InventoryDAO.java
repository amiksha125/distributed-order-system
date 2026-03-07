
package dao;

import config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InventoryDAO {

    public void addInventory(int productId, int quantity) {

        String sql = "INSERT INTO inventory(product_id, quantity) VALUES (?, ?)";

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, productId);
            pstmt.setInt(2, quantity);

            pstmt.executeUpdate();

            System.out.println("Inventory added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
