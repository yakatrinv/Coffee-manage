package it.academy.controller.commands.user;

import it.academy.controller.commands.Command;
import it.academy.services.auth.IUserService;
import it.academy.services.auth.UserService;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataAuth.ATTR_LOGIN;
import static it.academy.utils.DataGeneral.PREV_URL;

public class DeleteUser implements Command {
    private final IUserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(ATTR_LOGIN);
        if (StringUtils.isNotBlank(login)) {
            service.deleteUserByLogin(login);
        }

        return request.getParameter(PREV_URL);
    }
}
