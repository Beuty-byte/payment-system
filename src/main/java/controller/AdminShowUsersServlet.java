package controller;

import dao.UserDAOImpl;
import domain.Role;
import domain.SessionObjectForUser;
import domain.User;
import component.Pagination;
import service.UserService;
import service.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/admin/users")
public class AdminShowUsersServlet extends HttpServlet {

    UserService usersService = UsersServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionObjectForUser sessionObject = (SessionObjectForUser) request.getSession().getAttribute("isActive");
        if(sessionObject != null && sessionObject.getUserRole() == Role.ADMIN){

            Optional<String> page = Optional.ofNullable(request.getParameter("page"));
            Optional<String> sortBy = Optional.ofNullable(request.getParameter("sort"));

            List<User> allUsers = usersService.getAllUsers(page,sortBy);
            request.setAttribute("users", allUsers);

            int currentPage = 1;
            if(page.isPresent()){
                currentPage = Integer.parseInt(page.get());
            }
            int amountUsers = new UserDAOImpl().getAmountUsersInSystem();


            request.setAttribute("pagination",
                    new Pagination(currentPage, amountUsers , UserDAOImpl.getShowUsersOnPage()
                            , request.getRequestURI(), sortBy).get());


            request.getRequestDispatcher("/WEB-INF/view/adminShowUsers.jsp").forward(request, response);
        }else {
            response.sendRedirect("/sign-in");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
