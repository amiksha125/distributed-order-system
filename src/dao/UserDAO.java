package dao;

import config.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDAO {

    public void addUser(User user) {

        String sql = "INSERT INTO users(name,email) VALUES (?, ?)";

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());

            pstmt.executeUpdate();

            System.out.println("User added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
