package it.academy.controller.commands.user;

import it.academy.controller.commands.Command;
import it.academy.dto.auth.RoleDto;
import it.academy.services.auth.IRoleService;
import it.academy.services.auth.IUserService;
import it.academy.services.auth.RoleService;
import it.academy.services.auth.UserService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static it.academy.utils.DataAuth.ATTR_CHECK_ROLES;
import static it.academy.utils.DataAuth.ATTR_LOGIN;
import static it.academy.utils.DataGeneral.PREV_URL;

public class UpdateRolesUser implements Command {
    private final IUserService userService = new UserService();

    private final IRoleService roleService = new RoleService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(ATTR_LOGIN);
        String[] checkRoles = request.getParameterValues(ATTR_CHECK_ROLES);

        Set<RoleDto> roles = Arrays.stream(checkRoles)
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .map(roleService::findRoleById)
                .collect(Collectors.toSet());

        userService.updateRolesUser(login, roles);

        return request.getParameter(PREV_URL);
    }
}
