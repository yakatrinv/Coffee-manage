package it.academy.controller.commands.user;

import it.academy.controller.commands.Command;
import it.academy.services.auth.IRoleService;
import it.academy.services.auth.RoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ADD_USER_JSP;
import static it.academy.utils.Data.ATTR_ROLES;
import static it.academy.utils.Data.PREV_URL;

public class CreateUser implements Command {
    IRoleService service = new RoleService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));

        request.setAttribute(ATTR_ROLES, service.findAllRoles());

        return ADD_USER_JSP;
    }
}
