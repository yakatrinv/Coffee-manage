package it.academy.controller.commands.user;

import it.academy.controller.commands.Command;
import it.academy.dto.auth.UserDto;
import it.academy.services.auth.IUserService;
import it.academy.services.auth.UserService;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataAuth.ATTR_LOGIN;
import static it.academy.utils.DataAuth.ATTR_USER;
import static it.academy.utils.DataAuth.EDIT_PASS_USER_JSP;
import static it.academy.utils.DataGeneral.PREV_URL;

public class EditPassUser implements Command {
    private final IUserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserDto user;
        String login = request.getParameter(ATTR_LOGIN);
        if (StringUtils.isNotBlank(login)) {
            user = service.findUserByLogin(login);
            request.setAttribute(ATTR_USER, user);
        }

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_PASS_USER_JSP;
    }
}
