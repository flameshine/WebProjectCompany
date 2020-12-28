package com.flameshine.app.controller.worker;

import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.flameshine.app.database.*;

@WebServlet(name = "WorkerServlet", urlPatterns = "/worker")
public class WorkerServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(WorkerServlet.class);

    private final OrderDatabase orderDatabase = new OrderDatabase();
    private final UserDatabase userDatabase = new UserDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.setAttribute("workerOrders", orderDatabase.extractWorkerOrderData());
        req.getRequestDispatcher("view/roles/worker/workerHome.jsp").forward(req, resp);
        logger.info("Worker logged into his account...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {

            final int orderID = Integer.parseInt(req.getParameter("orderID"));
            final int orderStatusID = Integer.parseInt(req.getParameter("orderStatusID"));

            if (orderDatabase.validateOrderID(orderID)) {
                updateOrderStatus(orderID, orderStatusID);
                resp.getWriter().write(notifySuccess());
                logger.info("Worker successfully updated order status...");
            } else {
                resp.getWriter().write(notifyNonexistentID());
                logger.info("Worker entered nonexistent order ID...");
            }
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
            resp.getWriter().write(notifyIncorrectInput());
            logger.info("Worker entered incorrect order data...");
        }
    }

    private void updateOrderStatus(final int orderID, final int orderStatusID) {

        final int BEING_DEVELOPED = 3;
        final int DONE = 4;

        if (orderStatusID == BEING_DEVELOPED) {
            orderDatabase.changeOrderStatus(orderID, BEING_DEVELOPED);
            userDatabase.notifyUser(orderID, "Your order is under development!", BEING_DEVELOPED);
        } else if (orderStatusID == DONE) {
            orderDatabase.changeOrderStatus(orderID, DONE);
            userDatabase.notifyUser(orderID, "Your order is done!", DONE);
        }
    }

    private String notifyIncorrectInput() {
        return "<script>" + "alert('Incorrect input! Please, try again!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/worker';" + "</script>";
    }

    private String notifyNonexistentID() {
        return "<script>" + "alert('Incorrect order ID! Please, check your input!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/worker';" + "</script>";
    }

    private String notifySuccess() {
        return "<script>" + "alert('Order status updated!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/worker';" + "</script>";
    }
}