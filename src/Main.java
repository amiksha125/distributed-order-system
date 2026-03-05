import config.DBConnection;
import java.sql.Connection;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        try {

            Connection conn = DBConnection.getConnection();

            System.out.println("Database Connected Successfully!");

            Statement stmt = conn.createStatement();

            String sql = "CREATE DATABASE IF NOT EXISTS order_management";

            stmt.executeUpdate(sql);

            System.out.println("Database created.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}