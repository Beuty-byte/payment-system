package controller;

import domain.Role;
import domain.SessionObjectForUser;
import service.CheckedLoginDetails;
import service.VerificationOfLoginDetailsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * servlet for page /sign-in
 */
@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    private final CheckedLoginDetails checkedLoginDetails = VerificationOfLoginDetailsService.getInstance();

    /**
     * method for show sign-in page
     * @param request request
     * @param response response
     * @throws ServletException servlet exception
     * @throws IOException IO exception
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/sign-in.jsp").forward(request, response);
    }

    /**
     * method for validation user data and login
     * @param request request
     * @param response response
     * @throws ServletException servlet exception
     * @throws IOException IO exception
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            SessionObjectForUser sessionObjectForUser = checkedLoginDetails.loginDataValidation(email, password);
            HttpSession session = request.getSession();
            if(session.getAttribute("isActive") == null){
                session.setAttribute("isActive", sessionObjectForUser);
            }
            if(sessionObjectForUser.getUserRole() == Role.CUSTOMER) {
                response.sendRedirect("/account");
            }else if(sessionObjectForUser.getUserRole() == Role.ADMIN){
                response.sendRedirect("/admin");
            }
        }catch (IllegalArgumentException e){
            request.setAttribute("error", "wrong password or email");
            response.setStatus(401);
            doGet(request,response);
        }
    }
}
