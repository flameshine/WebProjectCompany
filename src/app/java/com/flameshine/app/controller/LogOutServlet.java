package com.flameshine.app.controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import org.apache.log4j.Logger;

@WebServlet(name="LogOutServlet", urlPatterns = "/logout")
public class LogOutServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LogOutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(req.getSession().getAttribute("username") + " logged out from his account...");
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}