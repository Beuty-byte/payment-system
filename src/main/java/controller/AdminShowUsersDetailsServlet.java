package controller;

import domain.User;
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
    private final AdderDataInSystem putDataInSystem= AdderDataInSystem.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Math.toIntExact(urlHandler.getIdFromUrl(request.getRequestURI()));
            User userById = userInfo.getUserById(userId);
            request.setAttribute("user",userById);
            request.getRequestDispatcher("/WEB-INF/view/adminShowUserDetails.jsp").forward(request, response);
        }catch (IllegalArgumentException e){
            response.setStatus(400);
            response.sendRedirect("/admin/users");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       putDataInSystem.putData(request.getParameterMap(), 0L);
       doGet(request,response);
    }
}
