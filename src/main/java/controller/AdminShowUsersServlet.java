package controller;

import domain.User;
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

/**
 * servlet for page /admin/users
 */
@WebServlet("/admin/users")
public class AdminShowUsersServlet extends HttpServlet {

    UserService usersService = UsersServiceImpl.getInstance();

    /**
     * method make forward at jsp page and show list with users
     * @param request request
     * @param response response
     * @throws ServletException servlet exception
     * @throws IOException IO exception
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Optional<String> page = Optional.ofNullable(request.getParameter("page"));
        Optional<String> sortBy = Optional.ofNullable(request.getParameter("sort"));

        try {
            List<User> allUsers = usersService.getAllUsers(page, sortBy);
            request.setAttribute("users", allUsers);
            request.setAttribute("pagination", usersService.getPaginationForUsers(page, sortBy, request.getRequestURI()));
            request.getRequestDispatcher("/WEB-INF/view/adminShowUsers.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            response.setStatus(400);
            response.sendRedirect("/admin/users");
        }
    }
}
