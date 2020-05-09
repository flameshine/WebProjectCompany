package servlets.manager;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import database.*;
import org.apache.log4j.Logger;

@WebServlet(name = "ManagerServlet", urlPatterns = "/manager")
public class ManagerServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ManagerServlet.class);

    private final OrderDatabase orderDatabase = new OrderDatabase();
    private final UserDatabase userDatabase = new UserDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.setAttribute("managerOrders", orderDatabase.extractManagerOrderData());
        req.getRequestDispatcher("view/roles/manager/managerHome.jsp").forward(req, resp);
        logger.info("Manager logged into his account...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {

            final int orderID = Integer.parseInt(req.getParameter("orderID"));
            final int orderStatusID = Integer.parseInt(req.getParameter("orderStatusID"));

            if (orderDatabase.validateOrderID(orderID)) {
                updateOrderStatus(req, orderID, orderStatusID);
                resp.getWriter().write(notifySuccess());
                logger.info("Manager successfully updated order status...");
            } else {
                resp.getWriter().write(notifyNonexistentID());
                logger.info("Manager entered nonexistent order ID...");
            }
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
            resp.getWriter().write(notifyIncorrectInput());
            logger.info("Manager entered incorrect order data...");
        }
    }

    private void updateOrderStatus(HttpServletRequest req, final int orderID, final int orderStatusID) {

        final int CONFIRMED = 2;
        final int REJECTED = 5;

        if (orderStatusID == CONFIRMED) {
            orderDatabase.changeOrderStatus(orderID, CONFIRMED);
            orderDatabase.changeOrderPrice(orderID, req.getParameter("orderPrice"));
            userDatabase.notifyUser(orderID, "Your order is confirmed!", CONFIRMED);
        } else if (orderStatusID == REJECTED) {
            orderDatabase.changeOrderStatus(orderID, REJECTED);
            userDatabase.notifyUser(orderID, req.getParameter("rejectionReason"), REJECTED);
        }
    }

    private String notifyIncorrectInput() {
        return "<script>" + "alert('Incorrect input! Please, try again!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/manager';" + "</script>";
    }

    private String notifyNonexistentID() {
        return "<script>" + "alert('Incorrect order ID! Please, check your input!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/manager';" + "</script>";
    }

    private String notifySuccess() {
        return "<script>" + "alert('Order information updated!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/manager';" + "</script>";
    }
}