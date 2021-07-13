package controller;

import domain.CreditCard;
import domain.Payment;
import domain.SessionObjectForUser;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;


@WebServlet("/account/payments/*")
public class PaymentDetailsServlet extends HttpServlet {

    private final PaymentService paymentService = PaymentServiceImpl.getInstance();
    private final CreditCardService creditCardService = CreditCardServiceImpl.getInstance();
    private final UrlHandler urlHandler = UrlHandlerImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionObjectForUser sessionObjectForUser = (SessionObjectForUser)request.getSession().getAttribute("isActive");
            try {
                int paymentId = Math.toIntExact(urlHandler.getIdFromUrl(request.getRequestURI()));
                Payment paymentById = paymentService.getPaymentById(paymentId);
                List<CreditCard> allCreditsCardForUser = creditCardService.getAllCreditsCardForUser(sessionObjectForUser.getUserId());
                request.setAttribute("paymentInfo", paymentById);
                request.setAttribute("userCreditCards", allCreditsCardForUser);
                request.getRequestDispatcher("/WEB-INF/view/paymentDetails.jsp").forward(request, response);
            }catch (IllegalArgumentException exception){
                response.setStatus(404);
                response.sendRedirect("/404");
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle lang = (ResourceBundle)request.getAttribute("lang");
        long creditCardId = Long.parseLong(request.getParameter("creditCardId"));
        int paymentId = Math.toIntExact(urlHandler.getIdFromUrl(request.getRequestURI()));
        List<String> errors = paymentService.doPayment(creditCardId, paymentId, lang);
        if (errors.size() == 0) {
            response.sendRedirect("/account/payments");
        } else {
            request.setAttribute("paymentError", errors);
            doGet(request, response);
        }
    }
}
