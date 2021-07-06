package controller;

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


@WebServlet("/account/credit-cards/*")
public class CreditCardDetailsServlet extends HttpServlet {

    private final CreditCardService creditCardInfo = CreditCardServiceImpl.getInstance();
    private final PutDataInSystem putDataInSystem = PutDataInSystem.getInstance();
    private final UrlHandler urlHandler = UrlHandlerImpl.getInstance();
    private final CheckDataWithFormService verifiableData = CheckedDataWithFormImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionObjectForUser sessionObject = (SessionObjectForUser)request.getSession().getAttribute("isActive");
        if(sessionObject != null && sessionObject.getUserRole() == Role.CUSTOMER){
            long creditCardId = urlHandler.getIdFromUrl(request.getRequestURI());
            domain.CreditCard creditCardById = creditCardInfo.getCreditCardById(creditCardId, sessionObject.getUserId());
            request.setAttribute("creditCardInfo", creditCardById);
            request.getRequestDispatcher("/WEB-INF/view/creditCardDetails.jsp").forward(request, response);
        }else {
            response.sendRedirect("/sign-in");
            }
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long creditCardId = urlHandler.getIdFromUrl(request.getRequestURI());
        List<String> errors = verifiableData.checkData(request.getParameterMap(), creditCardId);
        if(request.getParameter("block") != null){
            putDataInSystem.putData(request.getParameterMap(), creditCardId);
        }

        if(errors.size() > 0){
            request.setAttribute("errors",errors);
        }else {
            putDataInSystem.putData(request.getParameterMap(),creditCardId);
        }

        doGet(request,response);
    }

}
