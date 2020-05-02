package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

import database.OrderDatabase;

public class UserServlet extends HttpServlet {

    final OrderDatabase orderDatabase = new OrderDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/roles/user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String orderName = req.getParameter("orderName");
        final int priceOffer = Integer.parseInt(req.getParameter("priceOffer"));
        final String username = (String) req.getSession().getAttribute("username");

        try {
            orderDatabase.addNewOrder(username, orderName, priceOffer);
        } catch (SQLException exception) {
            throw new RuntimeException();
        }

        resp.getWriter().write(notifySuccess());
    }

    private String notifySuccess() {
        return "<script>" + "alert('Success! Soon your bid will be considered!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/user';" + "</script>";
    }
}