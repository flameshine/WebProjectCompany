package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import database.*;
import database.utils.*;

public class ManagerServlet extends HttpServlet {

    private final OrderDatabase orderDatabase = new OrderDatabase();
    private final UserDatabase userDatabase = new UserDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.setAttribute("managerOrders", orderDatabase.extractManagerOrderData());
        req.getRequestDispatcher("view/roles/manager.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        final int orderID = Integer.parseInt(req.getParameter("orderID"));
        final int orderStatusID = Integer.parseInt(req.getParameter("orderStatusID"));

        if (OrderValidator.validate(orderID)) {
            updateOrderStatus(req, orderID, orderStatusID);
            resp.getWriter().write(notifySuccess());
        }
        else
            resp.getWriter().write(notifyNonexistentID());
    }

    private void updateOrderStatus(HttpServletRequest req, final int orderID, final int orderStatusID) {

        final int CONFIRMED = 2;
        final int REJECTED = 5;

        final String SUCCESS = "Your order is confirmed!";

        if (orderStatusID == CONFIRMED) {
            orderDatabase.changeOrderStatus(orderID, CONFIRMED);
            orderDatabase.changeOrderPrice(orderID, req.getParameter("orderPrice"));
            userDatabase.notifyUser(orderID, SUCCESS);
        } else if (orderStatusID == REJECTED) {
            orderDatabase.changeOrderStatus(orderID, REJECTED);
            userDatabase.notifyUser(orderID, req.getParameter("rejectionReason"));
        }
    }

    private String notifyNonexistentID() {
        return "<script>" + "alert('Incorrect order ID! Please, check your input!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/manager';" + "</script>";
    }

    private String notifySuccess() {
        return "<script>" + "alert('Order information updated!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/manager';" + "</script>";
    }
}