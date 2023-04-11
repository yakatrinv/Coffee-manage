package it.academy.controller.commands.role;

import it.academy.controller.commands.Command;
import it.academy.dto.auth.RoleDto;
import it.academy.services.auth.IRoleService;
import it.academy.services.auth.RoleService;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataAuth.ATTR_ROLE;
import static it.academy.utils.DataAuth.EDIT_ROLE_JSP;
import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataGeneral.PREV_URL;

public class EditRole implements Command {
    private final IRoleService service = new RoleService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        RoleDto roleDto;
        String id = request.getParameter(ATTR_ID);
        if (StringUtils.isNotBlank(id)) {
            roleDto = service.findRoleById(Integer.parseInt(id));
            request.setAttribute(ATTR_ROLE, roleDto);
        }

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_ROLE_JSP;
    }
}
