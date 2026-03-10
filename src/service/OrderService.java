package service;

import config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderService {

    public void placeOrder(int userId, int productId, int quantity) {

        Connection conn = null;

        try {

            conn = DBConnection.getConnection();

            // Disable auto commit
            conn.setAutoCommit(false);

            /*
            STEP 1: Create Order
             */

            String orderSql = "INSERT INTO orders(user_id) VALUES (?)";

            PreparedStatement orderStmt = conn.prepareStatement(orderSql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            orderStmt.setInt(1, userId);

            orderStmt.executeUpdate();


            ResultSet rs = orderStmt.getGeneratedKeys();

            int orderId = 0;

            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            System.out.println("Order Created ID: " + orderId);


            /*
            STEP 2: Insert Order Item
             */

            String itemSql = "INSERT INTO order_items(order_id, product_id, quantity) VALUES (?, ?, ?)";

            PreparedStatement itemStmt = conn.prepareStatement(itemSql);

            itemStmt.setInt(1, orderId);
            itemStmt.setInt(2, productId);
            itemStmt.setInt(3, quantity);

            itemStmt.executeUpdate();

            System.out.println("Order item inserted");


            /*
            STEP 3: Deduct Inventory
             */

            String inventorySql = "UPDATE inventory SET quantity = quantity - ? WHERE product_id = ?";

            PreparedStatement invStmt = conn.prepareStatement(inventorySql);

            invStmt.setInt(1, quantity);
            invStmt.setInt(2, productId);

            invStmt.executeUpdate();

            System.out.println("Inventory updated");


            /*
            STEP 4: Commit transaction
             */

            conn.commit();

            System.out.println("Transaction Successful!");

        } catch (Exception e) {

            try {

                if (conn != null) {

                    conn.rollback();

                    System.out.println("Transaction Rolled Back!");

                }

            } catch (Exception ex) {

                ex.printStackTrace();

            }

            e.printStackTrace();
        }
    }
}