package it.academy.controller.commands.user;

import it.academy.controller.commands.Command;
import it.academy.services.auth.IUserService;
import it.academy.services.auth.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_LOGIN;
import static it.academy.utils.Data.ATTR_OLD_PASSWORD;
import static it.academy.utils.Data.ATTR_PASSWORD;
import static it.academy.utils.Data.PREV_URL;

public class UpdatePassUser implements Command {
    private final IUserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(ATTR_LOGIN);
        String password = request.getParameter(ATTR_PASSWORD);
        String oldPassword = request.getParameter(ATTR_OLD_PASSWORD);

        service.updatePassUser(login, password, oldPassword);

        return request.getParameter(PREV_URL);
    }
}
