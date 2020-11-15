package com.flameshine.app.model;

public class Notification {

    private int notificationID;

    private String orderName;
    private String username;
    private String notificationText;
    private String orderStatusMeaning;

    public Notification(String orderName, String notificationText, String orderStatusMeaning) {
        this.orderName = orderName;
        this.notificationText = notificationText;
        this.orderStatusMeaning = orderStatusMeaning;
    }

    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderID) {
        this.orderName = orderName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public String getOrderStatusMeaning() {
        return orderStatusMeaning;
    }

    public void setOrderStatusMeaning(String orderStatusMeaning) {
        this.orderStatusMeaning = orderStatusMeaning;
    }
}