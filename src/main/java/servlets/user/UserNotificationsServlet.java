package servlets.user;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import database.UserDatabase;

@WebServlet(name = "UserNotificationsServlet", urlPatterns = "/notifications")
public class UserNotificationsServlet extends HttpServlet {

    private final UserDatabase userDatabase = new UserDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");
        req.setAttribute("userNotifications", userDatabase.extractUserNotifications(username));
        req.getRequestDispatcher("view/roles/user/userNotifications.jsp").forward(req, resp);
    }
}