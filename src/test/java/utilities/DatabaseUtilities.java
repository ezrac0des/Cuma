package utilities;

import java.sql.*;

public class DatabaseUtilities {

    public static final String DB_URL = "jdbc:mysql://51.158.110.102:63306/hypnotes";
    public static final String USER = "hypnotes";
    public static final String PASS = "hypnotes";
    public static Connection connection = null; //bu baglanti
    public static Statement stmt = null; //bu query
    public static ResultSet rs = null; // bu cevap

    //endpointe baglaniyorduk -> requestimizi gonderiyorduk -> gelen cevap uzerinden kontrol yapiyorduk
    //database'e baglan -> query'i gonder -> gelen cevap uzerinden kontrol yapiyorduk

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void createStatement() {
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createResultSet(String query) {
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
