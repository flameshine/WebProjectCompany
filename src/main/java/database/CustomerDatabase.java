package database;

import java.sql.*;
import java.util.*;
import database.utils.*;
import models.Customer;

public class CustomerDatabase {

    public void registerCustomer(String username, String password) throws SQLException {
        if(!RegisterParser.parseMatches(username))
            Objects.requireNonNull(ConnectionPool.getConnection()).createStatement().executeUpdate(insertCustomerLoginData(username, password));
        else
            throw new RuntimeException();
    }

    public List<Customer> extractCustomerData() throws SQLException {
        ResultSet extractedData = ConnectionPool.createResultSet(selectCustomerData());
        return setUpCustomerList(extractedData);
    }

    public static void notifyCustomer(int orderID, String orderStatusMeaning) throws SQLException {
        ResultSet extractedCustomerID = ConnectionPool.createResultSet(selectCustomerIDByOrderID(orderID));
        if(extractedCustomerID.next()) {
            int customerID = extractedCustomerID.getInt(1);
            setUpCustomerList(ConnectionPool.createResultSet(selectCustomerData())).stream().filter(customer -> customer.getCustomerID() == customerID).forEach(customer -> customer.sendNotification(orderStatusMeaning));
        }
    }

    public static int getCustomerIDByName(String customerName) throws SQLException {
        return ConnectionPool.createResultSet(selectCustomerByName(customerName)).getInt(1);
    }

    private static List<Customer> setUpCustomerList(ResultSet extractedData) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        while(extractedData.next()) {
            Customer customer = new Customer(extractedData.getInt(1), extractedData.getString(2), extractedData.getString(3));
            customers.add(customer);
        }
        return customers;
    }

    private String insertCustomerLoginData(String username, String password) {
        return "INSERT INTO ITCompanyDataBase.userTable (userName, userPassword) VALUES ('" + username + "', '" + password + "')";
    }

    private static String selectCustomerIDByOrderID(int orderID) {
        return "SELECT clientID FROM ITCompanyDataBase.orderTable WHERE ITCompanyDataBase.orderTable.orderID = " + orderID;
    }

    private static String selectCustomerByName(String username) {
        return "SELECT * FROM ITCompanyDataBase.userTable WHERE userName = '" + username + "'";
    }

    private static String selectCustomerData() {
        return "SELECT userID, userName, userPassword FROM ITCompanyDataBase.userTable";
    }
}