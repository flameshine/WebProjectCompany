package com.flameshine.app.controller.user;

import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

@WebServlet(name = "UserServlet", urlPatterns = "/home")
public class UserServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/roles/user/userHome.jsp").forward(req, resp);
        logger.info(req.getSession().getAttribute("username") + " logged into his account...");
    }
}