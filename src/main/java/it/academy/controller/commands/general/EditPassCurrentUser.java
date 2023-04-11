package it.academy.controller.commands.general;

import it.academy.controller.commands.Command;
import it.academy.dto.auth.UserDto;
import it.academy.services.auth.IUserService;
import it.academy.services.auth.UserService;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataAuth.ATTR_LOGGED_USER;
import static it.academy.utils.DataAuth.ATTR_USER;
import static it.academy.utils.DataAuth.EDIT_PASS_USER_JSP;
import static it.academy.utils.DataGeneral.PREV_URL;

public class EditPassCurrentUser implements Command {
    private final IUserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserDto user = (UserDto) request.getSession().getAttribute(ATTR_LOGGED_USER);

        String login = user != null ? user.getLogin() : null;
        if (StringUtils.isNotBlank(login)) {
            user = service.findUserByLogin(login);
            request.setAttribute(ATTR_USER, user);
        }

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_PASS_USER_JSP;
    }
}
