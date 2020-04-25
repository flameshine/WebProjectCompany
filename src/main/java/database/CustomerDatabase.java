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

    public static int getUserIDByName(String customerName) throws SQLException {
        return ConnectionPool.createResultSet(selectUserByName(customerName)).getInt(1);
    }

    private List<Customer> setUpCustomerList(ResultSet extractedData) throws SQLException {
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

    private static String selectUserByName(String username) {
        return "SELECT * FROM ITCompanyDataBase.userTable WHERE userName = '" + username + "'";
    }

    private String selectCustomerData() {
        return "SELECT userID, userName, userPassword FROM ITCompanyDataBase.userTable";
    }
}