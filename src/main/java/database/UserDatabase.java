package database;

import java.sql.*;
import java.util.*;
import database.utils.*;
import models.User;

public class UserDatabase {

    private final List<User> users = extractUserData();

    public void registerUser(final String username, final String password) throws SQLException {
        if (!RegisterParser.parseMatches(username))
            Objects.requireNonNull(ConnectionPool.getConnection()).createStatement().executeUpdate(insertUserLoginData(username, password));
    }

    public User getUserByLoginData(final String username) {
        User resultUser = new User();
        for (User user : users) {
            if (user.getUsername().equals(username))
                resultUser = user;
        }
        return resultUser;
    }

    public static int getUserIDByName(final String username) throws SQLException {
        return Objects.requireNonNull(ConnectionPool.createResultSet(selectUserByName(username))).getInt(1);
    }

    public static void notifyUser(final int orderID, final String orderStatusMeaning) throws SQLException {
        ResultSet extractedUserID = ConnectionPool.createResultSet(selectUserIDByOrderID(orderID));
        if (extractedUserID.next()) {
            int userID = extractedUserID.getInt(1);
            setUpUserList(Objects.requireNonNull(ConnectionPool.createResultSet(selectUserData()))).stream().filter(user -> user.getUserID() == userID).forEach(user -> user.sendNotification(orderStatusMeaning));
        }
    }

    private List<User> extractUserData() {
        try {
            ResultSet extractedData = ConnectionPool.createResultSet(selectUserData());
            return setUpUserList(extractedData);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
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

    private static String selectUserIDByOrderID(final int orderID) {
        return "SELECT clientID FROM ITCompanyDataBase.orderTable WHERE ITCompanyDataBase.orderTable.orderID = " + orderID;
    }

    private static String selectUserByName(final String username) {
        return "SELECT * FROM ITCompanyDataBase.userTable WHERE userName = '" + username + "'";
    }

    private static String selectUserData() {
        return "SELECT userID, userName, userPassword FROM ITCompanyDataBase.userTable";
    }
}