package com.flameshine.app.util;

import java.util.Objects;
import java.sql.*;

public class ConnectionPool {

    private static final String
            DRIVER = "org.gjt.mm.mysql.Driver",
            URL = "jdbc:mysql://localhost/WebProjectCompany?&serverTimezone=UTC",
            USER = "root",
            PASSWORD = "password";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static ResultSet createResultSet(final String source) {
        try {
            var extractedData = Objects.requireNonNull(getConnection()).prepareStatement(source);
            return extractedData.executeQuery();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }
}