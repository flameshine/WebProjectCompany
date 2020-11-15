package com.flameshine.app.controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import org.apache.log4j.Logger;

@WebServlet(name = "AboutServlet", urlPatterns = "/about")
public class AboutServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AboutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/about.jsp").forward(req, resp);
        logger.info(req.getSession().getAttribute("username") + " entered the order creation page...");
    }
}