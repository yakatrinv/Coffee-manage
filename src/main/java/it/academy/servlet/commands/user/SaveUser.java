package it.academy.servlet.commands.user;

import it.academy.services.auth.IUserAuthService;
import it.academy.services.auth.UserAuthService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_LOGIN;
import static it.academy.utils.Data.ATTR_PASSWORD;
import static it.academy.utils.Data.ATTR_ROLES;
import static it.academy.utils.Data.PREV_URL;

public class SaveUser implements Command {
    private final IUserAuthService userAuthService = new UserAuthService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(ATTR_LOGIN);
        String password = request.getParameter(ATTR_PASSWORD);
        String roles = request.getParameter(ATTR_ROLES);

        userAuthService.createUser(login, password,roles);

        return request.getParameter(PREV_URL);
    }
}
