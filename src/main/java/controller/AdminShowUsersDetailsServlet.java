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

@WebServlet("/admin/users/*")
public class AdminShowUsersDetailsServlet extends HttpServlet {

    private final UserService userInfo = UsersServiceImpl.getInstance();
    private final UrlHandler urlHandler = UrlHandlerImpl.getInstance();
    private final PutDataInSystem putDataInSystem= PutDataInSystem.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionObjectForUser sessionObject = (SessionObjectForUser) request.getSession().getAttribute("isActive");
        if(sessionObject != null && sessionObject.getUserRole() == Role.ADMIN){

            int userId = Math.toIntExact(urlHandler.getIdFromUrl(request.getRequestURI()));
            domain.User userById = userInfo.getUserById(userId);
            request.setAttribute("user",userById);


            request.getRequestDispatcher("/WEB-INF/view/adminShowUserDetails.jsp").forward(request, response);
        }else {
            response.sendRedirect("/sign-in");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       putDataInSystem.putData(request.getParameterMap(), 0L);
       doGet(request,response);
    }
}
