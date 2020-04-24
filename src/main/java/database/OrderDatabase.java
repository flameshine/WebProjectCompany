package database;

import models.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDatabase {

    public List<Order> extractOrderData() throws ClassNotFoundException, SQLException {
        ResultSet extractedData = ConnectionPool.getInstance().createResultSet(selectOrderData());
        return setUpOrderList(extractedData);
    }

    private List<Order> setUpOrderList(ResultSet extractedData) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while(extractedData.next()) {
            Order order = new Order(extractedData.getInt(1), extractedData.getString(2), extractedData.getString(3), extractedData.getInt(4), extractedData.getString(5));
            orders.add(order);
        }
        return orders;
    }

    private String selectOrderData() {
        return "SELECT ITCompanyDataBase.orderTable.orderID, ITCompanyDataBase.userTable.userName, orderName, orderPrice, ITCompanyDataBase.orderStatusTable.statusMeaning FROM ITCompanyDataBase.orderTable JOIN ITCompanyDataBase.userTable ON (ITCompanyDataBase.userTable.userID = ITCompanyDataBase.orderTable.orderID) JOIN ITCompanyDataBase.orderStatusTable ON (ITCompanyDataBase.orderStatusTable.statusID = ITCompanyDataBase.orderTable.orderStatusID)";
    }
}