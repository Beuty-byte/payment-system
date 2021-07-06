package controller;

import domain.CreditCard;
import domain.Payment;
import domain.Role;
import domain.SessionObjectForUser;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



@WebServlet("/account/payments/*")
public class PaymentDetailsServlet extends HttpServlet {

    private final PaymentService paymentService = PaymentServiceImpl.getInstance();
    private final CreditCardService creditCardService = CreditCardServiceImpl.getInstance();
    private final UrlHandler urlHandler = UrlHandlerImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionObjectForUser sessionObjectForUser = (SessionObjectForUser)request.getSession().getAttribute("isActive");
        if(sessionObjectForUser != null && sessionObjectForUser.getUserRole() == Role.CUSTOMER){

            int paymentId = Math.toIntExact(urlHandler.getIdFromUrl(request.getRequestURI()));
            Payment paymentById = paymentService.getPaymentById(paymentId, sessionObjectForUser.getUserId());
            List<CreditCard> allCreditsCardForUser = creditCardService.getAllCreditsCardForUser(sessionObjectForUser.getUserId());
            request.setAttribute("paymentInfo", paymentById);
            request.setAttribute("userCreditCards", allCreditsCardForUser);

            request.getRequestDispatcher("/WEB-INF/view/paymentDetails.jsp").forward(request, response);
        }else {
            response.sendRedirect("/sign-in");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionObjectForUser sessionObjectForUser = (SessionObjectForUser) request.getSession().getAttribute("isActive");
        long creditCardId = Long.parseLong(request.getParameter("creditCardId"));
        domain.CreditCard creditCardById = creditCardService.getCreditCardById(creditCardId, sessionObjectForUser.getUserId());

        if(creditCardById != null){
            int paymentId = Math.toIntExact(urlHandler.getIdFromUrl(request.getRequestURI()));
            domain.Payment paymentById = paymentService.getPaymentById(paymentId, sessionObjectForUser.getUserId());
            List<String> errors = paymentService.doPayment(paymentById);

            if(errors.size() == 0){
                response.sendRedirect("/account/payments");
            }else {
                request.setAttribute("paymentError",errors);
                doGet(request,response);
            }
        }else {
            request.setAttribute("error", "You are bad hacker");
            doGet(request,response);
        }

    }
}
