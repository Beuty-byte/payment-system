package controller;

import service.CheckInUserInSystemImpl;
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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final VerifiableData validationRegisterForm = ValidationRegisterForm.getInstance();
    private final Registered registerUserInSystem = CheckInUserInSystemImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<String> registerErrors = validationRegisterForm.validate(request.getParameterMap());

        if(registerErrors.size() > 0){
            request.setAttribute("errors",registerErrors);
            doGet(request,response);
        }else if(!registerUserInSystem.registerInSystem(request.getParameterMap())){
            response.sendRedirect("/");
        }else {
            request.setAttribute("serverError","there was an error on the server, please try to register later");
            doGet(request,response);
        }
    }
}
