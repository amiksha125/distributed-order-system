package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
