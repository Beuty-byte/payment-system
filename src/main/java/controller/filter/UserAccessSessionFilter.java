package controller.filter;

import domain.Role;
import domain.SessionObjectForUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * web filter to check access to user page
 */
@WebFilter(urlPatterns = {"/account/payments", "/account/payments/*","/account/credit-cards"
        ,"/account/credit-cards/*","/account"})
public class UserAccessSessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        SessionObjectForUser sessionObjectForUser = (SessionObjectForUser) request.getSession().getAttribute("isActive");
        if(sessionObjectForUser != null && sessionObjectForUser.getUserRole() == Role.CUSTOMER){
            filterChain.doFilter(request,response);
        }else {
            response.sendRedirect("/sign-in");
        }
    }

    @Override
    public void destroy() {

    }
}
