package com.flameshine.app.controller.user;

import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import org.apache.log4j.Logger;

import com.flameshine.app.database.UserDatabase;

@WebServlet(name = "UserNotificationsServlet", urlPatterns = "/notifications")
public class UserNotificationsServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserNotificationsServlet.class);

    private final UserDatabase userDatabase = new UserDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");
        req.setAttribute("userNotifications", userDatabase.extractUserNotifications(username));
        req.getRequestDispatcher("view/roles/user/userNotifications.jsp").forward(req, resp);
        logger.info(req.getSession().getAttribute("username") + " viewed his notifications...");
    }
}