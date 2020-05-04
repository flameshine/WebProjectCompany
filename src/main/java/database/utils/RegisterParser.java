package database.utils;

import java.sql.*;

public class RegisterParser {

    public static boolean parseMatches(final String username) {
        try {
            ResultSet extractedData = ConnectionPool.createResultSet(extractLoginData());
            while (extractedData.next()) {
                if (username.equals(extractedData.getString(1)))
                    return true;
            }
            return false;
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    private static String extractLoginData() {
        return "SELECT userName FROM ITCompanyDataBase.userTable";
    }
}