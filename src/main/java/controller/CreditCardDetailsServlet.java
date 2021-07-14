package controller;

import domain.CreditCard;
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


@WebServlet("/account/credit-cards/*")
public class CreditCardDetailsServlet extends HttpServlet {

    private final CreditCardService creditCardService = CreditCardServiceImpl.getInstance();
    private final UrlHandler urlHandler = UrlHandlerImpl.getInstance();
    private final CheckDataWithFormService verifiableData = CheckedDataWithFormImpl.getInstance();
    private final static String BLOCK = "block";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionObjectForUser sessionObject = (SessionObjectForUser)request.getSession().getAttribute("isActive");
        long creditCardId = urlHandler.getIdFromUrl(request.getRequestURI());
        CreditCard creditCardById = creditCardService.getCreditCardById(creditCardId, sessionObject.getUserId());
        request.setAttribute("creditCardInfo", creditCardById);
        request.getRequestDispatcher("/WEB-INF/view/creditCardDetails.jsp").forward(request, response);
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle lang = (ResourceBundle) request.getAttribute("lang");
        long creditCardId = urlHandler.getIdFromUrl(request.getRequestURI());
        List<String> errors = verifiableData.checkData(request.getParameterMap(), creditCardId, lang);
        if(request.getParameter(BLOCK) != null){
            creditCardService.putData(request.getParameterMap(), creditCardId);
        }

        if(errors.size() > 0){
            request.setAttribute("errors",errors);
        }else {
            creditCardService.putData(request.getParameterMap(),creditCardId);
        }

        doGet(request,response);
    }

}
