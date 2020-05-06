package servlets.worker;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import database.utils.*;
import database.OrderDatabase;

@WebServlet(name = "WorkerServlet", urlPatterns = "/worker")
public class WorkerServlet extends HttpServlet {

    private final OrderDatabase orderDatabase = new OrderDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.setAttribute("workerOrders", orderDatabase.extractWorkerOrderData());
        req.getRequestDispatcher("view/roles/worker/workerHome.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {

            final int orderID = Integer.parseInt(req.getParameter("orderID"));
            final int orderStatusID = Integer.parseInt(req.getParameter("orderStatusID"));

            if (OrderValidator.validate(orderID)) {
                updateOrderStatus(orderID, orderStatusID);
                resp.getWriter().write(notifySuccess());
            } else
                resp.getWriter().write(notifyNonexistentID());
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
            resp.getWriter().write(notifyIncorrectInput());
        }
    }

    private void updateOrderStatus(final int orderID, final int orderStatusID) {

        final int BEING_DEVELOPED = 3;
        final int DONE = 4;

        if (orderStatusID == BEING_DEVELOPED)
            orderDatabase.changeOrderStatus(orderID, BEING_DEVELOPED);
        else if (orderStatusID == DONE)
            orderDatabase.changeOrderStatus(orderID, DONE);
    }

    private String notifyIncorrectInput() {
        return "<script>" + "alert('Incorrect input! Please, check your input!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/worker';" + "</script>";
    }

    private String notifyNonexistentID() {
        return "<script>" + "alert('Incorrect order ID! Please, check your input!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/worker';" + "</script>";
    }

    private String notifySuccess() {
        return "<script>" + "alert('Order status updated!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/worker';" + "</script>";
    }
}