package it.academy.servlet.commands.role;

import it.academy.dto.auth.RoleDto;
import it.academy.services.auth.IRoleService;
import it.academy.services.auth.RoleService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_ROLE;
import static it.academy.utils.Data.EDIT_ROLE_JSP;
import static it.academy.utils.Data.PREV_URL;

public class EditRole implements Command {
    private final IRoleService service = new RoleService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        RoleDto roleDto;
        String id = request.getParameter(ATTR_ID);
        if (id != null) {
            roleDto = service.findRoleById(Integer.valueOf(id));
            request.setAttribute(ATTR_ROLE, roleDto);
        }

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_ROLE_JSP;
    }
}
