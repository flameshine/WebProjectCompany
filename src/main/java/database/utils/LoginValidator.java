package database.utils;

import java.sql.*;

public class LoginValidator {

    public static boolean validate(final String username, final String password) throws SQLException {
        ResultSet extractedData = ConnectionPool.createResultSet(extractLoginData());
        while (extractedData.next()) {
            if (username.equals(extractedData.getString(1)) && password.equals(extractedData.getString(2)))
                return true;
        }
        return false;
    }

    private static String extractLoginData() {
        return "SELECT userName, userPassword FROM ITCompanyDataBase.userTable";
    }
}