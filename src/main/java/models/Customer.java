package models;

public class Customer {

    private int customerID;
    private String username;
    private String password;

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
}