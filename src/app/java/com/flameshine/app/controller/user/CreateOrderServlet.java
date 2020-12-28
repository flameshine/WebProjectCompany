package com.flameshine.app.controller.user;

import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.flameshine.app.database.OrderDatabase;

@WebServlet(name = "CreateOrderServlet", urlPatterns = "/create")
public class CreateOrderServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CreateOrderServlet.class);

    private final OrderDatabase orderDatabase = new OrderDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/roles/user/createOrder.jsp").forward(req, resp);
        logger.info(req.getSession().getAttribute("username") + " entered the order creation page...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        final var username = (String) req.getSession().getAttribute("username");
        final var orderName = req.getParameter("orderName");

        if (orderName != null) {
            orderDatabase.addNewOrder(username, orderName);
            resp.getWriter().write(notifySuccess());
            logger.info(req.getSession().getAttribute("username") + " added new order...");
        } else {
            resp.getWriter().write(notifyIncorrectInput());
            logger.info(req.getSession().getAttribute("username") + " entered incorrect order data...");
        }
    }

    private String notifyIncorrectInput() {
        return "<script>" + "alert('Incorrect input! Please, try again!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/create';" + "</script>";
    }

    private String notifySuccess() {
        return "<script>" + "alert('Success! Soon your bid will be considered!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/create';" + "</script>";
    }
}