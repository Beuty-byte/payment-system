package controller;

import domain.Role;
import domain.SessionObjectForUser;
import service.CheckedLoginDetails;
import service.VerificationOfLoginDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    private final CheckedLoginDetails checkedLoginDetails = VerificationOfLoginDetails.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/view/sign-in.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SessionObjectForUser sessionObjectForUser = checkedLoginDetails.dataChecking(request.getParameterMap());

        if(sessionObjectForUser == null){
            request.setAttribute("error", "wrong password or email");
            doGet(request,response);
        }else {
            HttpSession session = request.getSession();
            if(session.getAttribute("isActive") == null){
                session.setAttribute("isActive", sessionObjectForUser);
            }
            if(sessionObjectForUser.getUserRole() == Role.CUSTOMER) {
                response.sendRedirect("/account");
            }else {
                response.sendRedirect("/admin");
            }
        }
    }
}
