package it.academy.servlet.commands.role;

import it.academy.converter.IConverter;
import it.academy.converter.impl.RoleConverter;
import it.academy.dto.auth.RoleDto;
import it.academy.services.auth.IRoleService;
import it.academy.services.auth.RoleService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.PREV_URL;

public class SaveRole implements Command {
    private final IRoleService service = new RoleService();

    private final IConverter<RoleDto> converter = new RoleConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        RoleDto roleDto = converter.convertToDto(request);
        service.createRole(roleDto);

        return request.getParameter(PREV_URL);
    }
}