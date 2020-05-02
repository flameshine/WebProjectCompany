package database;

import java.sql.*;
import java.util.*;
import database.utils.*;
import models.User;

public class UserDatabase {

    public void registerUser(final String username, final String password) throws SQLException {
        if (!RegisterParser.parseMatches(username))
            Objects.requireNonNull(ConnectionPool.getConnection()).createStatement().executeUpdate(insertUserLoginData(username, password));
    }

    public static void notifyUser(final int orderID, final String orderStatusMeaning) throws SQLException {
        ResultSet extractedUsername = ConnectionPool.createResultSet(selectUsernameByOrderID(orderID));
        if (extractedUsername.next()) {
            String username = extractedUsername.getString(1);
            setUpUserList(Objects.requireNonNull(ConnectionPool.createResultSet(selectUserData()))).stream().filter(user -> user.getUsername().equals(username)).forEach(user -> user.sendNotification(orderStatusMeaning));
        }
    }

    private List<User> extractUserData() {
        try {
            ResultSet extractedData = ConnectionPool.createResultSet(selectUserData());
            return setUpUserList(extractedData);
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException();
        }
    }

    private static List<User> setUpUserList(ResultSet extractedData) throws SQLException {
        List<User> users = new ArrayList<>();
        while (extractedData.next())
            users.add(setUpUser(extractedData.getInt(1), extractedData.getString(2), extractedData.getString(3)));
        return users;
    }

    private static User setUpUser(int userID, String username, String password) {
        return new User(userID, username, password);
    }

    private String insertUserLoginData(final String username, final String password) {
        return "INSERT INTO ITCompanyDataBase.userTable (userName, userPassword) VALUES ('" + username + "', '" + password + "')";
    }

    private static String selectUsernameByOrderID(final int orderID) {
        return "SELECT username FROM ITCompanyDataBase.orderTable WHERE ITCompanyDataBase.orderTable.orderID = " + orderID;
    }

    private static String selectUserByName(final String username) {
        return "SELECT * FROM ITCompanyDataBase.userTable WHERE userName = '" + username + "'";
    }

    private static String selectUserData() {
        return "SELECT userID, userName, userPassword FROM ITCompanyDataBase.userTable";
    }
}