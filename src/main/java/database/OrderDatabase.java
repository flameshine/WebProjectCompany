package database;

import java.util.*;
import java.sql.*;

import models.*;
import database.utils.*;

//всі методи є тестовими і писались "на вскидку", так як далі ще буде робота з сервлетами і фронт-ендом.

public class OrderDatabase {

    private final int NOT_CONFIRMED = 1;
//    private final int CONFIRMED = 2;
//    private final int BEING_DEVELOPED = 3;
//    private final int DONE = 4;
//    private final int REJECTED = 5;

//додає нове замовлення
    public void addNewOrder(String customerName, String orderName, int orderPrice) throws SQLException {
        int customerID = CustomerDatabase.getUserIDByName(customerName);
        Objects.requireNonNull(ConnectionPool.getConnection()).createStatement().executeUpdate(insertNewOrder(customerID, orderName, orderPrice, NOT_CONFIRMED));
    }

//витягує всі замовлення певного користувача за іменем з б\д
    public void getCustomerOrders(String customerName) throws SQLException {
        int customerID = CustomerDatabase.getUserIDByName(customerName);
        Objects.requireNonNull(ConnectionPool.getConnection()).createStatement().executeUpdate(selectOrderDataByUserID(customerID));
    }

//змінює статус замовлення
    public void changeOrderStatus(int orderID, int statusID) throws SQLException {
        Objects.requireNonNull(ConnectionPool.getConnection()).createStatement().executeUpdate(updateOrderStatus(orderID, statusID));
//        notifyCustomer(orderID);
    }

//надсилає сповіщення користувачу, статус замовлення якого змінився
//    public void notifyCustomer(int orderID) throws SQLException {
//
//    }

//витягує всі замовлення з б\д
    private List<Order> extractOrderData() throws SQLException {
        ResultSet extractedData = ConnectionPool.createResultSet(selectOrderData());
        return setUpOrderList(extractedData);
    }

    private List<Order> setUpOrderList(ResultSet extractedData) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while (extractedData.next()) {
            Order order = new Order(extractedData.getInt(1), extractedData.getString(2), extractedData.getString(3), extractedData.getInt(4), extractedData.getString(5));
            orders.add(order);
        }
        return orders;
    }

    private String insertNewOrder(int customerID, String orderName, int orderPrice, int statusID) {
        return "INSERT INTO ITCompanyDataBase.orderTable (clientID, orderName, orderPrice, orderStatusID) VALUES (" + customerID + ", '" + orderName + "', " + orderPrice + ", " + statusID + ")";
    }

    private String selectOrderDataByUserID(int customerID) {
        return "SELECT ITCompanyDataBase.orderTable.orderID, ITCompanyDataBase.userTable.userName, orderName, orderPrice, ITCompanyDataBase.orderStatusTable.statusMeaning FROM ITCompanyDataBase.orderTable JOIN ITCompanyDataBase.userTable ON (ITCompanyDataBase.userTable.userID = ITCompanyDataBase.orderTable.clientID) JOIN ITCompanyDataBase.orderStatusTable ON (ITCompanyDataBase.orderStatusTable.statusID = ITCompanyDataBase.orderTable.orderStatusID) WHERE ITCompanyDataBase.userTable.userID = " + customerID;
    }

    private String updateOrderStatus(int orderID, int statusID) {
        return "UPDATE ITCompanyDataBase.orderTable SET orderStatusID = " + statusID + " WHERE orderID = " + orderID;
    }

    private String selectOrderData() {
        return "SELECT ITCompanyDataBase.orderTable.orderID, ITCompanyDataBase.userTable.userName, orderName, orderPrice, ITCompanyDataBase.orderStatusTable.statusMeaning FROM ITCompanyDataBase.orderTable JOIN ITCompanyDataBase.userTable ON (ITCompanyDataBase.userTable.userID = ITCompanyDataBase.orderTable.clientID) JOIN ITCompanyDataBase.orderStatusTable ON (ITCompanyDataBase.orderStatusTable.statusID = ITCompanyDataBase.orderTable.orderStatusID)";
    }
}