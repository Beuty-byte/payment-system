package controller;

import service.RegisterUserInSystem;
import service.Registered;
import service.ValidationRegisterForm;
import service.VerifiableData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * servlet for page register
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final VerifiableData validationRegisterForm = ValidationRegisterForm.getInstance();
    private final Registered registerUserInSystem = RegisterUserInSystem.getInstance();

    /**
     * method show register page
     * @param request request
     * @param response response
     * @throws ServletException servlet exception
     * @throws IOException IO exception
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
    }

    /**
     * method to accept user data and their validation
     * @param request request
     * @param response response
     * @throws ServletException servlet exception
     * @throws IOException IO exception
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle lang = (ResourceBundle) request.getAttribute("lang");
        List<String> registerErrors = validationRegisterForm.validate(request.getParameterMap(), lang);

        if(registerErrors.size() > 0){
            request.setAttribute("errors",registerErrors);
            response.setStatus(400);
            doGet(request,response);
        }else if(!registerUserInSystem.registerInSystem(request.getParameterMap())){
            response.sendRedirect("/");
        }else {
            request.setAttribute("serverError","there was an error on the server, please try to register later");
            doGet(request,response);
        }
    }
}
