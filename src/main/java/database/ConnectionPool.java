package database;

import java.sql.*;
import java.util.Objects;

public class ConnectionPool {

    private static ConnectionPool instance = null;
    private static Connection connection = null;

    private static final String
            DRIVER = "com.mysql.cj.jdbc.Driver",
            URL = "jdbc:mysql://localhost/ITCompanyDataBase?&serverTimezone=UTC",
            USER = "root",
            PASSWORD = "toortoor";

    public static synchronized ConnectionPool getInstance() {
        if(instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    public synchronized Connection getConnection() throws ClassNotFoundException, SQLException {
        if(connection == null)
            connection = createConnection();
        return connection;
    }

    public ResultSet createResultSet(String source) throws ClassNotFoundException, SQLException {
        PreparedStatement extractedData = Objects.requireNonNull(getConnection()).prepareStatement(source);
        return extractedData.executeQuery();
    }

    private ConnectionPool() {}

    private static Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}