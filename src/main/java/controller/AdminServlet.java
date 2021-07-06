package controller;

import domain.Role;
import domain.SessionObjectForUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionObjectForUser sessionObject = (SessionObjectForUser) request.getSession().getAttribute("isActive");

        if(sessionObject != null && sessionObject.getUserRole() == Role.ADMIN){
            request.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(request, response);
        }else {
            response.sendRedirect("/sign-in");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
