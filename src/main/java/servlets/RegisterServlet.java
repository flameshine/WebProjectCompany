package servlets;

import database.CustomerDatabase;
import database.utils.RegisterParser;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {

    CustomerDatabase customerDatabase = new CustomerDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String firstPasswordAttempt = req.getParameter("firstPasswordAttempt");
        String secondPasswordAttempt = req.getParameter("secondPasswordAttempt");

        try {
            if (!RegisterParser.parseMatches(username) && firstPasswordAttempt.equals(secondPasswordAttempt)) {
                req.getRequestDispatcher("view/login.jsp").forward(req, resp);
                customerDatabase.registerCustomer(username, firstPasswordAttempt);
            }
            else
                resp.getWriter().println("Something wrong!");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}