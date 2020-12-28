package com.flameshine.app.controller.user;

import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.flameshine.app.database.OrderDatabase;

@WebServlet(name = "UserOrderServlet", urlPatterns = "/orders")
public class UserOrdersServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserOrdersServlet.class);

    private final OrderDatabase orderDatabase = new OrderDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var username = (String) req.getSession().getAttribute("username");
        req.setAttribute("userOrders", orderDatabase.extractUserOrders(username));
        req.getRequestDispatcher("view/roles/user/userOrders.jsp").forward(req, resp);
        logger.info(req.getSession().getAttribute("username") + " viewed his orders...");
    }
}