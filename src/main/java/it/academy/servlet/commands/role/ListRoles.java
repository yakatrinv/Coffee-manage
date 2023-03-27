package it.academy.servlet.commands.role;

import it.academy.converter.IConverter;
import it.academy.converter.IRoleConverter;
import it.academy.converter.impl.PageableConverter;
import it.academy.converter.impl.RoleConverter;
import it.academy.dto.auth.RoleDto;
import it.academy.models.pageable.Pageable;
import it.academy.services.auth.IRoleService;
import it.academy.services.auth.RoleService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_ROLE_NAME;
import static it.academy.utils.Data.ATTR_SEARCH_ROLE_NAME;
import static it.academy.utils.Data.PAGEABLE;
import static it.academy.utils.Data.ROLES_JSP;


public class ListRoles implements Command {
    private final IConverter<Pageable<RoleDto>> converter = new PageableConverter<>();

    private final IRoleService service = new RoleService();

    private final IRoleConverter roleConverter = new RoleConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<RoleDto> pageableDto = converter.convertToDto(request);
        pageableDto.setSearchFields(roleConverter.convertSearchFields(request));

        //заглушка
        //добавить возможность сортировки по полям
        pageableDto.setSortField(ATTR_ID);

        pageableDto = service.getPageableRecords(pageableDto);
        request.setAttribute(PAGEABLE, pageableDto);

        if (pageableDto.getSearchFields().containsKey(ATTR_ROLE_NAME)) {
            request.setAttribute(ATTR_SEARCH_ROLE_NAME,
                    pageableDto.getSearchFields().get(ATTR_ROLE_NAME));
        }


        return ROLES_JSP;
    }
}
