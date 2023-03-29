package it.academy.controller.commands.auth;

import it.academy.controller.commands.Command;
import it.academy.dto.auth.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static it.academy.utils.Data.ATTR_LOGGED_USER;
import static it.academy.utils.Data.LOGIN_JSP;
import static it.academy.utils.Data.MAIN_JSP;

public class GetLoginPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        UserDto userDto = (UserDto) session.getAttribute(ATTR_LOGGED_USER);
        if (userDto != null) {
            response.sendRedirect(MAIN_JSP);
        } else {
            session.invalidate();
        }

        return LOGIN_JSP;
    }
}
