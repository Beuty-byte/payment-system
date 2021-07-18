package controller;

import domain.CreditCard;
import domain.SessionObjectForUser;
import service.CreditCardService;
import service.CreditCardServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * servlet for page /account/credit-cards
 */
@WebServlet("/account/credit-cards")
public class CreditCardsServlet extends HttpServlet {

    private final CreditCardService creditCardInfo = CreditCardServiceImpl.getInstance();

    /**
     * method show all credit cards for user and total balance
     * @param request request
     * @param response response
     * @throws ServletException servlet exception
     * @throws IOException IO exception
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionObjectForUser sessionObject = (SessionObjectForUser)request.getSession().getAttribute("isActive");
        List<CreditCard> creditCardList =  creditCardInfo.getAllCreditsCardForUser(sessionObject.getUserId());
        String totalBalance = creditCardInfo.getTotalBalance(sessionObject.getUserId());
        request.setAttribute("totalBalance", totalBalance);
        request.setAttribute("creditCards", creditCardList);
        request.getRequestDispatcher("/WEB-INF/view/creditCards.jsp").forward(request, response);
    }
}
