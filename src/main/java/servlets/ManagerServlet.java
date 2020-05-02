package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import database.OrderDatabase;

public class ManagerServlet extends HttpServlet {

    final OrderDatabase orderDatabase = new OrderDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.setAttribute("orders", orderDatabase.extractOrderData());
        req.getRequestDispatcher("view/roles/manager.jsp").forward(req, resp);
    }
}