package dao;

import config.DBConnection;
import model.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderItemDAO {

    public void addOrderItem(OrderItem item) {

        String sql = "INSERT INTO order_items(order_id, product_id, quantity) VALUES (?, ?, ?)";

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, item.getOrderId());
            pstmt.setInt(2, item.getProductId());
            pstmt.setInt(3, item.getQuantity());

            pstmt.executeUpdate();

            System.out.println("Order item inserted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}