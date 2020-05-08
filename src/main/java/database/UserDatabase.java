package database;

import java.sql.*;
import java.util.*;
import database.utils.*;
import models.Notification;

public class UserDatabase {

    public void registerUser(final String username, final String password) {
        try {
            Objects.requireNonNull(ConnectionPool.getConnection()).createStatement().executeUpdate(insertUserLoginData(username, password));
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Notification> extractUserNotifications(final String username) {
        ResultSet extractedData = ConnectionPool.createResultSet(selectUserNotifications(username));
        return setUpNotificationList(extractedData);
    }

    public void notifyUser(final int orderID, final String notificationText, final int orderStatusID) {
        try {
            String username = getUsernameByOrderID(orderID);
            ConnectionPool.getConnection().createStatement().executeUpdate(insertNewNotification(orderID, username, notificationText, orderStatusID));
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    public boolean validateLoginData(final String username, final String password) {
        try {
            ResultSet extractedData = ConnectionPool.createResultSet(extractLoginData());
            while (extractedData.next()) {
                if (username.equals(extractedData.getString(1)) && password.equals(extractedData.getString(2)))
                    return true;
            }
            return false;
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    public boolean parseUsernameMatches(final String username) {
        try {
            ResultSet extractedData = ConnectionPool.createResultSet(extractUsernameData());
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

    private String getUsernameByOrderID(final int orderID) {
        try {
            ResultSet extractedUsername = ConnectionPool.createResultSet(selectUsernameByOrderID(orderID));
            if (extractedUsername.next())
                return extractedUsername.getString(1);
            else
                throw new RuntimeException();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    @org.jetbrains.annotations.NotNull
    private List<Notification> setUpNotificationList(ResultSet extractedData) {
        try {
            List<Notification> notifications = new ArrayList<>();
            while (extractedData.next())
                notifications.add(new Notification(extractedData.getString(1), extractedData.getString(2), extractedData.getString(3)));
            return notifications;
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    @org.jetbrains.annotations.NotNull
    private String insertUserLoginData(final String username, final String password) {
        return "INSERT INTO ITCompanyDataBase.userTable (username, userPassword) VALUES ('" + username + "', '" + password + "')";
    }

    @org.jetbrains.annotations.NotNull
    private String insertNewNotification(final int orderID, final String username, final String notificationText, final int orderStatusID) {
        return "INSERT INTO ITCompanyDataBase.userNotificationTable (orderID, username, text, orderStatusID) VALUES (" + orderID + ", '" + username + "', '" + notificationText + "', " + orderStatusID + ")";
    }

    @org.jetbrains.annotations.NotNull
    private String selectUserNotifications(final String username) {
        return "SELECT ITCompanyDataBase.orderTable.orderName, text, ITCompanyDataBase.orderStatusTable.statusMeaning FROM ITCompanyDataBase.userNotificationTable JOIN ITCompanyDataBase.orderTable ON (ITCompanyDataBase.orderTable.orderID = ITCompanyDataBase.userNotificationTable.orderID) JOIN ITCompanyDataBase.orderStatusTable ON (ITCompanyDataBase.orderStatusTable.statusID = ITCompanyDataBase.userNotificationTable.orderStatusID) WHERE ITCompanyDataBase.userNotificationTable.username = '" + username + "' ORDER BY ID DESC";
    }

    @org.jetbrains.annotations.NotNull
    private String selectUsernameByOrderID(final int orderID) {
        return "SELECT username FROM ITCompanyDataBase.orderTable WHERE ITCompanyDataBase.orderTable.orderID = " + orderID;
    }

    @org.jetbrains.annotations.NotNull
    private String extractLoginData() {
        return "SELECT username, userPassword FROM ITCompanyDataBase.userTable";
    }

    @org.jetbrains.annotations.NotNull
    private String extractUsernameData() {
        return "SELECT username FROM ITCompanyDataBase.userTable";
    }
}