package com.flameshine.app.controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import com.flameshine.app.database.UserDatabase;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private final UserDatabase userDatabase = new UserDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        final HttpSession session = req.getSession();

        final String username = req.getParameter("username");
        final String password = req.getParameter("password");

        if (userDatabase.validateLoginData(username, password)) {
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            moveToMenu(req, resp);
        }
        else
            resp.getWriter().write(notifyIncorrectLoginInput());
    }

    private void moveToMenu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameter("username").equals("manager"))
            resp.sendRedirect(req.getContextPath() + "/manager");
        else if (req.getParameter("username").equals("worker"))
            resp.sendRedirect(req.getContextPath() + "/worker");
        else
            resp.sendRedirect(req.getContextPath() + "/home");
    }

    @org.jetbrains.annotations.NotNull
    private String notifyIncorrectLoginInput() {
        return "<script>" + "alert('Incorrect login or password! Please, check your input.');" + "window.location = 'http://localhost:8080/WebProjectITCompany/login';" + "</script>";
    }
}