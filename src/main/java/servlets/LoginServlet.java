package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import database.UserDatabase;
import database.utils.LoginValidator;
import models.User;

public class LoginServlet extends HttpServlet {

    private final UserDatabase userDatabase = new UserDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final HttpSession session = req.getSession();

        final String username = req.getParameter("username");
        final String password = req.getParameter("password");

        final User.ROLE role = userDatabase.getUserByLoginData(username).getUserRole();

        if (checkLoginData(username, password)) {
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            session.setAttribute("role", role);
            resp.sendRedirect("http://localhost:8080/WebProjectITCompany/home");
        }
        else
            resp.getWriter().write(notifyIncorrectLoginInput());
    }

    private boolean isAdmin(String username) {
        return userDatabase.getUserByLoginData(username).getUserRole() == User.ROLE.ADMIN;
    }

    private boolean checkLoginData(final String username, final String password) {
        try {
            if (LoginValidator.validate(username, password))
                return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    private String notifyIncorrectLoginInput() {
        return "<script>" + "alert('Incorrect login or password! Please, check your input.');" + "window.location = 'http://localhost:8080/WebProjectITCompany/login';" + "</script>";
    }
}