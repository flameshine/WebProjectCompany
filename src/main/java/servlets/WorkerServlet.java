package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import database.utils.*;
import database.OrderDatabase;

public class WorkerServlet extends HttpServlet {

    private final int BEING_DEVELOPED = 3;
    private final int DONE = 4;

    private final OrderDatabase orderDatabase = new OrderDatabase();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.setAttribute("workerOrders", orderDatabase.extractWorkerOrderData());
        req.getRequestDispatcher("view/roles/worker.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        final int orderID = Integer.parseInt(req.getParameter("orderID"));
        final int orderStatusID = Integer.parseInt(req.getParameter("orderStatusID"));

        if (OrderValidator.validate(orderID)) {
            updateOrderStatus(orderID, orderStatusID);
            resp.getWriter().write(notifySuccess());
        }
        else
            resp.getWriter().write(notifyNonexistentID());
    }

    private void updateOrderStatus(final int orderID, final int orderStatusID) {
        if (orderStatusID == BEING_DEVELOPED)
            orderDatabase.changeOrderStatus(orderID, BEING_DEVELOPED);
        else if (orderStatusID == DONE)
            orderDatabase.changeOrderStatus(orderID, DONE);
    }

    private String notifyNonexistentID() {
        return "<script>" + "alert('Incorrect order ID! Please, check your input!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/worker';" + "</script>";
    }

    private String notifySuccess() {
        return "<script>" + "alert('Order status updated!');" + "window.location = 'http://localhost:8080/WebProjectITCompany/worker';" + "</script>";
    }
}