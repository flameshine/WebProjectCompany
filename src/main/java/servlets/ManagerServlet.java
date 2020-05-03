package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import database.utils.*;
import database.OrderDatabase;

public class ManagerServlet extends HttpServlet {

    private final int CONFIRMED = 2;
    private final int REJECTED = 5;

    final OrderDatabase orderDatabase = new OrderDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.setAttribute("orders", orderDatabase.extractOrderData());
        req.getRequestDispatcher("view/roles/manager.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        final int orderID = Integer.parseInt(req.getParameter("orderID"));
        final int orderStatusID = Integer.parseInt(req.getParameter("orderStatusID"));

        if (checkOrderID(orderID)) {
            if (orderStatusID == CONFIRMED)
                orderDatabase.changeOrderStatus(orderID, CONFIRMED);
            else
                orderDatabase.changeOrderStatus(orderID, REJECTED);
            resp.getWriter().write(notifySuccess());
        }
        else
            resp.getWriter().write(notifyNonexistentID());
    }

    private boolean checkOrderID(final int orderID) {
        try {
            if (OrderValidator.validate(orderID))
                return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    private String notifyNonexistentID() {
        return "<script>" + "alert('Incorrect order ID! Please, check your input!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/manager';" + "</script>";
    }

    private String notifySuccess() {
        return "<script>" + "alert('Order status updated!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/manager';" + "</script>";
    }
}