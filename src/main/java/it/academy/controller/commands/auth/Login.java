package it.academy.controller.commands.auth;

import it.academy.controller.commands.Command;
import it.academy.dto.auth.UserDto;
import it.academy.services.auth.IUserService;
import it.academy.services.auth.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static it.academy.utils.Data.ATTR_LOGGED_ROLE;
import static it.academy.utils.Data.ATTR_LOGGED_USER;
import static it.academy.utils.Data.ATTR_LOGIN;
import static it.academy.utils.Data.ATTR_MESSAGE;
import static it.academy.utils.Data.ATTR_PASSWORD;
import static it.academy.utils.Data.ATTR_USER_ROLES;
import static it.academy.utils.Data.ERROR_AUTH;
import static it.academy.utils.Data.ERROR_JSP;
import static it.academy.utils.Data.ERROR_ROLE;
import static it.academy.utils.Data.LOGIN_JSP;
import static it.academy.utils.Data.MAIN_JSP;

public class Login implements Command {
    IUserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute(ATTR_LOGGED_USER);

        if (userDto != null) {
            return MAIN_JSP;
        } else {
            String login = request.getParameter(ATTR_LOGIN);
            String password = request.getParameter(ATTR_PASSWORD);

            if (StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
                return LOGIN_JSP;
            } else {
                UserDto authUser = service.findAuthUser(login, password);
                session.setAttribute(ATTR_LOGGED_USER, authUser);

                if (authUser == null) {
                    request.setAttribute(ATTR_MESSAGE, ERROR_AUTH);
                    return ERROR_JSP;
                } else {
                    session.setAttribute(ATTR_USER_ROLES, authUser.getRoles());

                    if (CollectionUtils.isEmpty(authUser.getRoles())) {
                        request.setAttribute(ATTR_MESSAGE, ERROR_ROLE);
                        return ERROR_JSP;
                    } else {
                        session.setAttribute(ATTR_USER_ROLES, authUser.getRoles());
                        session.setAttribute(ATTR_LOGGED_ROLE, authUser.getRoles().iterator().next());

                        return MAIN_JSP;
                    }
                }
            }
        }
    }

}
