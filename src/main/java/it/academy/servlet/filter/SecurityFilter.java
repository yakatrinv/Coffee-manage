package it.academy.servlet.filter;

import it.academy.dto.auth.UserAuthDto;
import it.academy.services.auth.UserAuthService;

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
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static it.academy.utils.Data.ATTR_COMMAND;
import static it.academy.utils.Data.ATTR_LOGGED_USER;
import static it.academy.utils.Data.ATTR_USER_ROLES;
import static it.academy.utils.Data.ERROR_JSP;
import static it.academy.utils.Data.GET_LOGIN_PAGE;
import static it.academy.utils.Data.GET_REG_PAGE;
import static it.academy.utils.Data.LOGIN_COMMAND;
import static it.academy.utils.Data.LOGIN_JSP;
import static it.academy.utils.Data.MAIN_JSP;

@WebFilter(urlPatterns = {"/*"})
public class SecurityFilter implements Filter {
    UserAuthService userAuthService = new UserAuthService();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession();

        String command = servletRequest.getParameter(ATTR_COMMAND);
        if (GET_LOGIN_PAGE.equalsIgnoreCase(command)
                || GET_REG_PAGE.equalsIgnoreCase(command)) {
            getLoginOrRegPage(servletRequest, servletResponse, filterChain, session);
        } else if (LOGIN_COMMAND.equalsIgnoreCase(command)) {
            loginFilter(servletRequest, servletResponse, filterChain, req, session);
        } else {
            filterChain.doFilter(req, res);
        }
    }

    private void loginFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain, HttpServletRequest req, HttpSession session) throws IOException, ServletException {
        UserAuthDto userDto = (UserAuthDto) session.getAttribute(ATTR_LOGGED_USER);

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        if (userDto != null) {
            ((HttpServletResponse) servletResponse).sendRedirect(MAIN_JSP);
        } else if (login == null || login.isEmpty()
                || password == null || password.isEmpty()) {
            ((HttpServletResponse) servletResponse).sendRedirect(LOGIN_JSP);
        } else {
            UserAuthDto authUser = null;
            try {
                authUser = userAuthService.findAuthUser(login, password);
            } catch (NoSuchAlgorithmException
                     | RuntimeException
                     | InvalidKeySpecException e) {
                e.printStackTrace();
            }

            session.setAttribute(ATTR_LOGGED_USER, authUser);

            if (authUser == null) {
                session.setAttribute(ATTR_USER_ROLES, authUser.getRoles());
                ((HttpServletResponse) servletResponse).sendRedirect(ERROR_JSP);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    private static void getLoginOrRegPage(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain, HttpSession session) throws IOException, ServletException {
        UserAuthDto userDto = (UserAuthDto) session.getAttribute(ATTR_LOGGED_USER);
        if (userDto != null) {
            ((HttpServletResponse) servletResponse).sendRedirect(MAIN_JSP);
        } else {
            session.invalidate();
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
