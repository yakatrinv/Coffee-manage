package it.academy.controller.commands.role;

import it.academy.controller.commands.Command;
import it.academy.services.auth.IRoleService;
import it.academy.services.auth.RoleService;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.PREV_URL;

public class DeleteRole implements Command {
    private final IRoleService service = new RoleService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(ATTR_ID);
        if (StringUtils.isBlank(id)) {
            service.deleteAddressById(Integer.parseInt(id));
        }
        return request.getParameter(PREV_URL);
    }
}
