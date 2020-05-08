package servlets.user;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import database.OrderDatabase;

@WebServlet(name = "CreateOrderServlet", urlPatterns = "/create")
public class CreateOrderServlet extends HttpServlet {

    private final OrderDatabase orderDatabase = new OrderDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/roles/user/createOrder.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        final String username = (String) req.getSession().getAttribute("username");
        final String orderName = req.getParameter("orderName");

        if (orderName != null) {
            orderDatabase.addNewOrder(username, orderName);
            resp.getWriter().write(notifySuccess());
        } else
            resp.getWriter().write(notifyIncorrectInput());
    }

    @org.jetbrains.annotations.NotNull
    private String notifyIncorrectInput() {
        return "<script>" + "alert('Incorrect input! Please, try again!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/create';" + "</script>";
    }

    @org.jetbrains.annotations.NotNull
    private String notifySuccess() {
        return "<script>" + "alert('Success! Soon your bid will be considered!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/create';" + "</script>";
    }
}