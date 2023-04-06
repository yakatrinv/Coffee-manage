package it.academy.controller.filter;

import it.academy.dto.auth.UserDto;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static it.academy.utils.DataAuth.ATTR_LOGGED_USER;
import static it.academy.utils.DataAuth.LOGIN_JSP;

@WebFilter(urlPatterns = {"/view/*"})
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws ServletException, IOException {

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        UserDto loggedUser = (UserDto) session.getAttribute(ATTR_LOGGED_USER);

        if (loggedUser == null) {
            ((HttpServletResponse) servletResponse).sendRedirect(LOGIN_JSP);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
