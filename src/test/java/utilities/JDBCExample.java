package utilities;

import java.sql.*;

public class JDBCExample extends DatabaseUtilities{
//    static final String DB_URL = "jdbc:mysql://127.0.0.1";
//    static final String USER = "root";
//    static final String PASS = "";
//    static final String QUERY = "SELECT * FROM users";

//    static final String DB_URL = "jdbc:mysql://51.158.107.22:6332";
//    static final String USER = "hypnotes";
//    static final String PASS = "hypnotes";
    static final String QUERY = "SELECT * FROM users";

    public static void main(String[] args) {
        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY);
        ) {
            while (rs.next()) {
                //Display values
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
