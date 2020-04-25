package database.utils;

import java.sql.*;
import java.util.Objects;
import java.beans.PropertyVetoException;

public class ConnectionPool {

    public static Connection getConnection() {
        try {
            return DBSetUpUtil.setUpService().getConnection();
        } catch (PropertyVetoException | SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static ResultSet createResultSet(String source) throws SQLException {
        PreparedStatement extractedData = Objects.requireNonNull(getConnection()).prepareStatement(source);
        return extractedData.executeQuery();
    }
}