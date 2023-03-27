package it.academy.servlet.commands.user;

import it.academy.services.auth.IUserAuthService;
import it.academy.services.auth.UserAuthService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_LOGIN;
import static it.academy.utils.Data.ATTR_OLD_PASSWORD;
import static it.academy.utils.Data.ATTR_PASSWORD;
import static it.academy.utils.Data.PREV_URL;

public class UpdatePassUser implements Command {
    private final IUserAuthService userAuthService = new UserAuthService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(ATTR_LOGIN);
        String password = request.getParameter(ATTR_PASSWORD);
        String oldPassword = request.getParameter(ATTR_OLD_PASSWORD);

        userAuthService.updatePassUser(login, password, oldPassword);

        return request.getParameter(PREV_URL);
    }
}
