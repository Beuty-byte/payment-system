package controller;

import domain.Role;
import domain.SessionObjectForUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet for index page
 */
@WebServlet("/")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionObjectForUser sessionObjectForUser = (SessionObjectForUser) request.getSession().getAttribute("isActive");
        if(sessionObjectForUser != null && sessionObjectForUser.getUserRole() == Role.CUSTOMER){
            request.setAttribute("referenceToAccount","/account");
        }
        if(sessionObjectForUser != null && sessionObjectForUser.getUserRole() == Role.ADMIN){
            request.setAttribute("referenceToAccount","/admin");
        }

        request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
    }
}
