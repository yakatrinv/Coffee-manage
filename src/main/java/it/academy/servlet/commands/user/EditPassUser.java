package it.academy.servlet.commands.user;

import it.academy.dto.auth.UserAuthDto;
import it.academy.services.auth.IUserAuthService;
import it.academy.services.auth.UserAuthService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_LOGIN;
import static it.academy.utils.Data.ATTR_USER;
import static it.academy.utils.Data.EDIT_PASS_USER_JSP;
import static it.academy.utils.Data.PREV_URL;

public class EditPassUser implements Command {
    private final IUserAuthService userAuthService = new UserAuthService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserAuthDto user;
        String login = request.getParameter(ATTR_LOGIN);
        if (login != null && !login.isEmpty()) {
            user = userAuthService.finfByLogin(login);
            request.setAttribute(ATTR_USER, user);
        }

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_PASS_USER_JSP;
    }
}
