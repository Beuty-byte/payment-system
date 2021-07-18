package controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * web filter to set internationalization
 */
@WebFilter("*")
public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        Cookie[] cookies = request.getCookies();

        Locale locale = Locale.ENGLISH;
        ResourceBundle lang = ResourceBundle.getBundle("text", locale);
        if(cookies != null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("lang")){
                    locale = new Locale("ru","RUS");
                    lang = ResourceBundle.getBundle("text",locale);
                }
            }
        }

        request.setAttribute("lang",lang);
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
