package models;

public class User {

    private int userID;
    private String username;
    private String password;
    private ROLE userRole;

    public User(int userID, String username, String password, ROLE userRole) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public User() {}

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUserRole(ROLE userRole) {
        this.userRole = userRole;
    }

    public ROLE getUserRole() {
        return userRole;
    }

    public enum ROLE {
        ADMIN, WORKER, USER
    }

    public void sendNotification(String orderStatusMeaning) {

    }
}