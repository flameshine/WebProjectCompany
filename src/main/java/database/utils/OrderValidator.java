package database.utils;

import java.sql.*;

public class OrderValidator {

    public static boolean validate(final int orderID) {
        try {
            ResultSet extractedData = ConnectionPool.createResultSet(extractLoginData());
            while (extractedData.next()) {
                if (orderID == extractedData.getInt(1))
                    return true;
            }
            return false;
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    private static String extractLoginData() {
        return "SELECT orderID FROM ITCompanyDataBase.orderTable";
    }
}