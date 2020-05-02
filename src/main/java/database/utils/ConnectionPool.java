package database.utils;

import java.sql.*;
import java.util.Objects;

public class ConnectionPool {

    private static final String
            DRIVER = "org.gjt.mm.mysql.Driver",
            URL = "jdbc:mysql://localhost/ITCompanyDataBase?&serverTimezone=UTC",
            USER = "root",
            PASSWORD = "toortoor";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static ResultSet createResultSet(String source) throws SQLException {
        PreparedStatement extractedData = Objects.requireNonNull(getConnection()).prepareStatement(source);
        return extractedData.executeQuery();
    }
}