package database;

import java.sql.*;
import java.util.*;
import database.utils.*;
import models.User;

public class UserDatabase {

    public void registerUser(final String username, final String password) {
        try {
            if (!RegisterParser.parseMatches(username))
                Objects.requireNonNull(ConnectionPool.getConnection()).createStatement().executeUpdate(insertUserLoginData(username, password));
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void notifyUser(final int orderID, final String feedback) {
        try {
            ResultSet extractedUsername = ConnectionPool.createResultSet(selectUsernameByOrderID(orderID));
            if (extractedUsername.next()) {
                String username = extractedUsername.getString(1);
                setUpUserList(Objects.requireNonNull(ConnectionPool.createResultSet(selectUserData()))).stream().filter(user -> user.getUsername().equals(username)).forEach(user -> user.sendNotification(feedback));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    private List<User> setUpUserList(ResultSet extractedData) {
        try {
            List<User> users = new ArrayList<>();
            while (extractedData.next())
                users.add(new User(extractedData.getInt(1), extractedData.getString(2), extractedData.getString(3)));
            return users;
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    private String insertUserLoginData(final String username, final String password) {
        return "INSERT INTO ITCompanyDataBase.userTable (userName, userPassword) VALUES ('" + username + "', '" + password + "')";
    }

    private String selectUsernameByOrderID(final int orderID) {
        return "SELECT username FROM ITCompanyDataBase.orderTable WHERE ITCompanyDataBase.orderTable.orderID = " + orderID;
    }

    private String selectUserData() {
        return "SELECT userID, userName, userPassword FROM ITCompanyDataBase.userTable";
    }
}