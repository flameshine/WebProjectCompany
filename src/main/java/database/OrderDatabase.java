package database;

import java.util.*;
import java.sql.*;
import database.utils.*;
import models.Order;

public class OrderDatabase {

    public void addNewOrder(final String username, final String orderName, final int priceOffer) throws SQLException {
        Objects.requireNonNull(ConnectionPool.getConnection()).createStatement().executeUpdate(insertNewOrder(username, orderName, priceOffer, 1));
    }

    public List<Order> extractOrderData() {
        try {
            ResultSet extractedData = ConnectionPool.createResultSet(selectOrderData());
            return setUpOrderList(extractedData);
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void changeOrderStatus(final int orderID, final int statusID) {
        try {
            Objects.requireNonNull(ConnectionPool.getConnection()).createStatement().executeUpdate(updateOrderStatus(orderID, statusID));
            ResultSet extractedStatusMeaning = ConnectionPool.createResultSet(selectOrderStatusMeaningByOrderID(orderID));
            if (extractedStatusMeaning.next())
                UserDatabase.notifyUser(orderID, extractedStatusMeaning.getString(1));
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Order> extractUserOrders(final String username) throws SQLException {
        List<Order> userOrders = new ArrayList<>();
        ResultSet extractedData = ConnectionPool.createResultSet(selectOrderDataByUsername(username));
        while(extractedData.next())
            userOrders.add(new Order(extractedData.getInt(1), extractedData.getString(2), extractedData.getString(3), extractedData.getInt(4), extractedData.getString(5)));
        return userOrders;
    }

    private List<Order> setUpOrderList(ResultSet extractedData) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while (extractedData.next())
            orders.add(new Order(extractedData.getInt(1), extractedData.getString(2), extractedData.getString(3), extractedData.getInt(4), extractedData.getString(5)));
        return orders;
    }

    private String insertNewOrder(final String username, final String orderName, final int priceOffer, final int statusID) {
        return "INSERT INTO ITCompanyDataBase.orderTable (username, orderName, priceOffer, orderStatusID) VALUES ('" + username + "', '" + orderName + "', " + priceOffer + ", " + statusID + ")";
    }

    private String updateOrderStatus(final int orderID, final int statusID) {
        return "UPDATE ITCompanyDataBase.orderTable SET orderStatusID = " + statusID + " WHERE orderID = " + orderID;
    }

    private String selectOrderDataByUsername(final String username) {
        return "SELECT ITCompanyDataBase.orderTable.orderID, username, orderName, priceOffer, ITCompanyDataBase.orderStatusTable.statusMeaning FROM ITCompanyDataBase.orderTable JOIN ITCompanyDataBase.orderStatusTable ON (ITCompanyDataBase.orderStatusTable.statusID = ITCompanyDataBase.orderTable.orderStatusID) WHERE username = '" + username + "'";
    }

    private String selectOrderStatusMeaningByOrderID(final int orderID) {
        return "SELECT ITCompanyDataBase.orderStatusTable.statusMeaning FROM ITCompanyDataBase.orderTable JOIN ITCompanyDataBase.orderStatusTable ON (ITCompanyDataBase.orderStatusTable.statusID = ITCompanyDataBase.orderTable.orderStatusID) WHERE ITCompanyDataBase.orderTable.orderID = " + orderID;
    }

    private String selectOrderData() {
        return "SELECT ITCompanyDataBase.orderTable.orderID, username, orderName, priceOffer, ITCompanyDataBase.orderStatusTable.statusMeaning FROM ITCompanyDataBase.orderTable JOIN ITCompanyDataBase.orderStatusTable ON (ITCompanyDataBase.orderStatusTable.statusID = ITCompanyDataBase.orderTable.orderStatusID)";
    }
}