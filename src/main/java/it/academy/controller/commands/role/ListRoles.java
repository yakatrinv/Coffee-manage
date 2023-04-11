package it.academy.controller.commands.role;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.IRoleConverter;
import it.academy.converter.impl.PageableConverter;
import it.academy.converter.impl.RoleConverter;
import it.academy.dto.auth.RoleDto;
import it.academy.models.pageable.Pageable;
import it.academy.services.auth.IRoleService;
import it.academy.services.auth.RoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataAuth.ATTR_ROLE_NAME;
import static it.academy.utils.DataAuth.ATTR_SEARCH_ROLE_NAME;
import static it.academy.utils.DataAuth.ROLES_JSP;
import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataPageable.PAGEABLE;


public class ListRoles implements Command {
    private final IConverter<Pageable<RoleDto>> converterP = new PageableConverter<>();

    private final IRoleService service = new RoleService();

    private final IRoleConverter converter = new RoleConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<RoleDto> pageableDto = converterP.convertToDto(request);
        pageableDto.setSearchFields(converter.convertSearchFields(request));

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
