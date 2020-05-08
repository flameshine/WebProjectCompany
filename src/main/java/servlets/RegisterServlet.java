package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import database.UserDatabase;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private final UserDatabase userDatabase = new UserDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        final String username = req.getParameter("username");
        final String firstPasswordAttempt = req.getParameter("firstPasswordAttempt");
        final String secondPasswordAttempt = req.getParameter("secondPasswordAttempt");

        if (userDatabase.parseUsernameMatches(username))
            resp.getWriter().write(notifyExistingUsername());
        else if (firstPasswordAttempt.equals(secondPasswordAttempt)) {
            userDatabase.registerUser(username, firstPasswordAttempt);
            resp.getWriter().write(notifySuccess());
        }
        else
            resp.getWriter().write(notifyIncorrectPasswordConfirmation());
    }

    @org.jetbrains.annotations.NotNull
    private String notifyExistingUsername() {
        return "<script>" + "alert('This username is already exists! Please, pick another one.');" + "window.location = 'http://localhost:8080/WebProjectITCompany/register';" + "</script>";
    }

    @org.jetbrains.annotations.NotNull
    private String notifyIncorrectPasswordConfirmation() {
        return "<script>" + "alert('Incorrect password confirmation! Please, check your input.');" + "window.location = 'http://localhost:8080/WebProjectITCompany/register';" + "</script>";
    }

    @org.jetbrains.annotations.NotNull
    private String notifySuccess() {
        return "<script>" + "alert('Congratulations! You have been successfully registered!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/login';" + "</script>";
    }
}