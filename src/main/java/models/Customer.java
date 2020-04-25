package models;

public class Customer {

    private final int customerID;
    private final String username;
    private final String password;

    public Customer(int customerID, String username, String password) {
        this.customerID = customerID;
        this.username = username;
        this.password = password;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

//    надсилає сповіщення про змінення статусу замовлення
//    public void sendNotification(String orderStatusMeaning) {
//        System.out.println("Your order status was changed! Current status: " + orderStatusMeaning);
//    }
}