package controller;


import domain.Payment;
import domain.Role;
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

@WebServlet("/account/payments")
public class PaymentServlet extends HttpServlet {

    private final PaymentService paymentService = PaymentServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionObjectForUser sessionObject = (SessionObjectForUser)request.getSession().getAttribute("isActive");

        if(sessionObject != null && sessionObject.getUserRole() == Role.CUSTOMER){
            List<Payment> payments = paymentService.getAllPaymentsByUserId(sessionObject.getUserId());
            request.setAttribute("payments", payments);
            request.getRequestDispatcher("/WEB-INF/view/payment.jsp").forward(request, response);
        }else {
            response.sendRedirect("/sign-in");
        }
    }

}
