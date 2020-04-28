package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import models.User;

public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final User.ROLE userRole = (User.ROLE) req.getSession().getAttribute("role");
        moveToMenu(req, resp, userRole);
    }

    private void moveToMenu(final HttpServletRequest req, final HttpServletResponse resp, final User.ROLE role) throws ServletException, IOException {
        if (role.equals(User.ROLE.ADMIN))
            req.getRequestDispatcher("view/adminHome.jsp").forward(req, resp);
        else if (role.equals(User.ROLE.WORKER))
            req.getRequestDispatcher("view/workerHome.jsp").forward(req, resp);
        else if (role.equals(User.ROLE.USER))
            req.getRequestDispatcher("view/userHome.jsp").forward(req, resp);
    }
}