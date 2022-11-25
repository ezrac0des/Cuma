package utilities;

import java.sql.*;

public class DatabaseUtilities {

    public static final String DB_URL = "jdbc:mysql://51.158.107.22:6336/hypnotes";
    public static final String USER = "hypnotes";
    public static final String PASS = "hypnotes";
    public static Connection connection = null;
    public static PreparedStatement stmt = null;
    public static ResultSet rs = null;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void createStatement(String qry) throws SQLException {
        stmt = connection.prepareStatement(qry,
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY
        );
    }

    public static void createResultSet(String query) throws SQLException {
        rs = stmt.executeQuery(query);
    }
}
