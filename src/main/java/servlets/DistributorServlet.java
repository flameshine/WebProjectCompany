package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import models.User;

public class DistributorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("role").equals(User.ROLE.ADMIN))
            resp.sendRedirect(req.getContextPath() + "/admin");
        else if (req.getSession().getAttribute("role").equals(User.ROLE.WORKER))
            resp.sendRedirect(req.getContextPath() + "/worker");
        else if (req.getSession().getAttribute("role").equals(User.ROLE.USER))
            resp.sendRedirect(req.getContextPath() + "/user");
        else
            resp.sendRedirect(req.getContextPath() + "/login");
    }
}