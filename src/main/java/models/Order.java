package models;

public class Order {

    private int orderID;
    private String username;
    private String orderName;
    private int orderPrice;
    private String orderStatusMeaning;

    public Order(int orderID, String username, String orderName, int orderPrice, String orderStatusMeaning) {
        this.orderID = orderID;
        this.username = username;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.orderStatusMeaning = orderStatusMeaning;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderStatusMeaning(String orderStatusMeaning) {
        this.orderStatusMeaning = orderStatusMeaning;
    }

    public String getOrderStatusMeaning() {
        return orderStatusMeaning;
    }
}