package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import database.utils.LoginValidator;

public class LoginServlet extends HttpServlet {

    private final String MANAGER_USERNAME = "manager";
    private final String WORKER_USERNAME = "worker";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final HttpSession session = req.getSession();

        final String username = req.getParameter("username");
        final String password = req.getParameter("password");

        if (checkLoginData(username, password)) {
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            moveToMenu(req, resp);
        }
        else
            resp.getWriter().write(notifyIncorrectLoginInput());
    }

    private void moveToMenu(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        if (req.getSession().getAttribute("username").equals(MANAGER_USERNAME))
            resp.sendRedirect(req.getContextPath() + "/manager");
        else if (req.getSession().getAttribute("username").equals(WORKER_USERNAME))
            resp.sendRedirect(req.getContextPath() + "/worker");
        else
            resp.sendRedirect(req.getContextPath() + "/user");
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