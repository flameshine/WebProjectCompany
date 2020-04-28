package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import database.UserDatabase;
import database.utils.RegisterParser;

public class RegisterServlet extends HttpServlet {

    private final UserDatabase userDatabase = new UserDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String username = req.getParameter("username");
        final String firstPasswordAttempt = req.getParameter("firstPasswordAttempt");
        final String secondPasswordAttempt = req.getParameter("secondPasswordAttempt");

        if (checkUsername(username))
            resp.getWriter().write(notifyExistingUsername());
        if (firstPasswordAttempt.equals(secondPasswordAttempt)) {
            registerUser(username, firstPasswordAttempt);
            resp.sendRedirect("http://localhost:8080/WebProjectITCompany/login");
        }
        else
            resp.getWriter().write(notifyIncorrectPasswordConfirmation());
    }

    private boolean checkUsername(final String username) {
        try {
            return RegisterParser.parseMatches(username);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    private void registerUser(final String username, final String password) {
        try {
            userDatabase.registerUser(username, password);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private String notifyExistingUsername() {
        return "<script>" + "alert('This username is already exists! Please, pick another one.');" + "window.location = 'http://localhost:8080/WebProjectITCompany/register';" + "</script>";
    }

    private String notifyIncorrectPasswordConfirmation() {
        return "<script>" + "alert('Incorrect password confirmation! Please, check your input.');" + "window.location = 'http://localhost:8080/WebProjectITCompany/register';" + "</script>";
    }
}