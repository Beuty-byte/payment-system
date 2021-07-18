package controller;


import domain.Payment;
import domain.SessionObjectForUser;
import service.PaymentService;
import service.PaymentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * servlet for page /account/payments
 */
@WebServlet("/account/payments")
public class PaymentServlet extends HttpServlet {

    private final PaymentService paymentService = PaymentServiceImpl.getInstance();

    /**
     * method show all payments for user
     * @param request request
     * @param response response
     * @throws ServletException servlet exception
     * @throws IOException IO exception
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionObjectForUser sessionObject = (SessionObjectForUser)request.getSession().getAttribute("isActive");
        List<Payment> payments = paymentService.getAllPaymentsByUserId(sessionObject.getUserId());
        request.setAttribute("payments", payments);
        request.getRequestDispatcher("/WEB-INF/view/payment.jsp").forward(request, response);
    }

}
