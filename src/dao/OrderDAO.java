package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBConnection;
import model.Order;

public class OrderDAO {

    private Connection conn;

    public OrderDAO(Connection conn) {
        this.conn = conn;
    }

    public void addOrder(Order order) throws SQLException {

        String sql = "INSERT INTO orders (user_id) VALUES (?)";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, order.getUserId());

        stmt.executeUpdate();

        System.out.println("Order added successfully!");
    }

    public void getOrdersByUser(int userId) {

        String sql = "SELECT * FROM orders WHERE user_id = ?";

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            System.out.println("Orders for user ID: " + userId);

            while (rs.next()) {

                int orderId = rs.getInt("order_id");
                String orderDate = rs.getString("order_date");

                System.out.println("Order ID: " + orderId + " Date: " + orderDate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
