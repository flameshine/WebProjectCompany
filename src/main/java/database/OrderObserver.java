package database;

import java.sql.SQLException;

public interface OrderObserver {
    void changeOrderStatus(String orderName, int statusID) throws SQLException;
    void notifyObserver(String name) throws SQLException;
}
