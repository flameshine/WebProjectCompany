package database.utils;

import java.sql.*;

public class RegisterParser {

    public static boolean parseMatches(String username) throws SQLException {
        ResultSet extractedData = ConnectionPool.createResultSet(extractLoginData());
        while (extractedData.next()) {
            if (username.equals(extractedData.getString(1)))
                return true;
        }
        return false;
    }

    private static String extractLoginData() {
        return "SELECT userName FROM ITCompanyDataBase.userTable";
    }
}