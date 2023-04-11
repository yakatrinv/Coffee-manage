package it.academy.controller.commands.user;

import it.academy.controller.commands.Command;
import it.academy.dto.auth.RoleDto;
import it.academy.dto.auth.UserDto;
import it.academy.services.auth.IRoleService;
import it.academy.services.auth.IUserService;
import it.academy.services.auth.RoleService;
import it.academy.services.auth.UserService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static it.academy.utils.DataAuth.ATTR_LOGIN;
import static it.academy.utils.DataAuth.ATTR_ROLES;
import static it.academy.utils.DataAuth.ATTR_USER;
import static it.academy.utils.DataAuth.EDIT_ROLES_USER_JSP;
import static it.academy.utils.DataGeneral.PREV_URL;

public class EditRolesUser implements Command {
    private final IUserService userService = new UserService();
    private final IRoleService roleService = new RoleService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserDto user;
        String login = request.getParameter(ATTR_LOGIN);
        if (StringUtils.isNotBlank(login)) {
            user = userService.findUserByLogin(login);
            request.setAttribute(ATTR_USER, user);
        }

        List<RoleDto> roles = roleService.findAllRoles();
        request.setAttribute(ATTR_ROLES, roles);

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_ROLES_USER_JSP;
    }
}
