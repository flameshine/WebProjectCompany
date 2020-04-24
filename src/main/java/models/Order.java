package models;

public class Order {

    private final int orderID;
    private final String clientName;
    private final String orderName;
    private final int orderPrice;
    private final String orderStatusMeaning;

    public Order(int orderID, String clientName, String orderName, int orderPrice, String orderStatusMeaning) {
        this.orderID = orderID;
        this.clientName = clientName;
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.orderStatusMeaning = orderStatusMeaning;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getClientName() {
        return clientName;
    }

    public String getOrderName() {
        return orderName;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public String getOrderStatusMeaning() {
        return orderStatusMeaning;
    }
}