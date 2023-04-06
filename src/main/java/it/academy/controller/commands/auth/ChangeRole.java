package it.academy.controller.commands.auth;

import it.academy.controller.commands.Command;
import it.academy.dto.auth.RoleDto;
import it.academy.services.auth.IRoleService;
import it.academy.services.auth.RoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static it.academy.utils.DataAuth.ATTR_LOGGED_ROLE;
import static it.academy.utils.DataAuth.ATTR_ROLE;
import static it.academy.utils.DataGeneral.MAIN_JSP;

public class ChangeRole implements Command {
    IRoleService service = new RoleService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        String roleName = request.getParameter(ATTR_ROLE);
        RoleDto role = service.findRoleByName(roleName);

        session.setAttribute(ATTR_LOGGED_ROLE, role);

        return MAIN_JSP;
    }
}
