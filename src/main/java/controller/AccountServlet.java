package controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet for page /account
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    /**
     *  method just forward at jsp page
     * @param request request
     * @param response response
     * @throws ServletException servlet exception
     * @throws IOException input output exception
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("/WEB-INF/view/account.jsp").forward(request, response);
    }

}
