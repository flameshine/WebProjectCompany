package database;

import java.util.*;
import java.sql.*;
import models.Order;
import database.utils.*;

public class OrderDatabase {

    private final int NOT_CONFIRMED = 1;
//    private final int CONFIRMED = 2;
//    private final int BEING_DEVELOPED = 3;
//    private final int DONE = 4;
//    private final int REJECTED = 5;

    private final List<Order> orders = extractOrderData();

    public void addNewOrder(final String username, final String orderName, final int orderPrice) throws SQLException {
        int userID = UserDatabase.getUserIDByName(username);
        Objects.requireNonNull(ConnectionPool.getConnection()).createStatement().executeUpdate(insertNewOrder(userID, orderName, orderPrice, NOT_CONFIRMED));
    }

    public void changeOrderStatus(final int orderID, final int statusID) throws SQLException {
        Objects.requireNonNull(ConnectionPool.getConnection()).createStatement().executeUpdate(updateOrderStatus(orderID, statusID));
        ResultSet extractedStatusMeaning = ConnectionPool.createResultSet(selectOrderStatusMeaningByOrderID(orderID));
        if (extractedStatusMeaning.next())
            UserDatabase.notifyUser(orderID, extractedStatusMeaning.getString(1));
    }

    public void getUserOrders(final String username) throws SQLException {
        int userID = UserDatabase.getUserIDByName(username);
        Objects.requireNonNull(ConnectionPool.getConnection()).createStatement().executeUpdate(selectOrderDataByUserID(userID));
    }

    private List<Order> extractOrderData() {
        try {
            ResultSet extractedData = ConnectionPool.createResultSet(selectOrderData());
            return setUpOrderList(extractedData);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private List<Order> setUpOrderList(ResultSet extractedData) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while (extractedData.next()) {
            Order order = new Order(extractedData.getInt(1), extractedData.getString(2), extractedData.getString(3), extractedData.getInt(4), extractedData.getString(5));
            orders.add(order);
        }
        return orders;
    }

    private String insertNewOrder(final int userID, final String orderName, final int orderPrice, final int statusID) {
        return "INSERT INTO ITCompanyDataBase.orderTable (clientID, orderName, orderPrice, orderStatusID) VALUES (" + userID + ", '" + orderName + "', " + orderPrice + ", " + statusID + ")";
    }

    private String updateOrderStatus(final int orderID, final int statusID) {
        return "UPDATE ITCompanyDataBase.orderTable SET orderStatusID = " + statusID + " WHERE orderID = " + orderID;
    }

    private String selectOrderDataByUserID(final int userID) {
        return "SELECT ITCompanyDataBase.orderTable.orderID, ITCompanyDataBase.userTable.userName, orderName, orderPrice, ITCompanyDataBase.orderStatusTable.statusMeaning FROM ITCompanyDataBase.orderTable JOIN ITCompanyDataBase.userTable ON (ITCompanyDataBase.userTable.userID = ITCompanyDataBase.orderTable.clientID) JOIN ITCompanyDataBase.orderStatusTable ON (ITCompanyDataBase.orderStatusTable.statusID = ITCompanyDataBase.orderTable.orderStatusID) WHERE ITCompanyDataBase.userTable.userID = " + userID;
    }

    private String selectOrderStatusMeaningByOrderID(final int orderID) {
        return "SELECT ITCompanyDataBase.orderStatusTable.statusMeaning FROM ITCompanyDataBase.orderTable JOIN ITCompanyDataBase.userTable ON (ITCompanyDataBase.userTable.userID = ITCompanyDataBase.orderTable.clientID) JOIN ITCompanyDataBase.orderStatusTable ON (ITCompanyDataBase.orderStatusTable.statusID = ITCompanyDataBase.orderTable.orderStatusID) WHERE ITCompanyDataBase.orderTable.orderID = " + orderID;
    }

    private String selectOrderData() {
        return "SELECT ITCompanyDataBase.orderTable.orderID, ITCompanyDataBase.userTable.userName, orderName, orderPrice, ITCompanyDataBase.orderStatusTable.statusMeaning FROM ITCompanyDataBase.orderTable JOIN ITCompanyDataBase.userTable ON (ITCompanyDataBase.userTable.userID = ITCompanyDataBase.orderTable.clientID) JOIN ITCompanyDataBase.orderStatusTable ON (ITCompanyDataBase.orderStatusTable.statusID = ITCompanyDataBase.orderTable.orderStatusID)";
    }
}