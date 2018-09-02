package servlet;

import enteties.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class Filter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String command = request.getParameter("command");
        String uri = request.getRequestURI();

        if (command == null) {
            switch (uri) {
                case "/login":
                    if (request.getSession().getAttribute("userRole") == null) {
                        request.setAttribute("redirect", "login.jsp");
                    } else {
                        request.setAttribute("redirect", "index.jsp");
                    }
                    break;
                case "/register":
                    if (request.getSession().getAttribute("userRole") == null) {
                        request.setAttribute("redirect", "register.jsp");
                    } else {
                        request.setAttribute("redirect", "index.jsp");
                    }
                    break;
                case "/ship":
                    request.setAttribute("command", "shipPage");
                    break;
                case "/":
                    request.setAttribute("redirect", "index.jsp");
                    break;
                case "/adminPage":
                    request.setAttribute("command","adminPageCommand");
                    break;

            }
        }


        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
