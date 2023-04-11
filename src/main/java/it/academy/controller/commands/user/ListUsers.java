package it.academy.controller.commands.user;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.IUserConverter;
import it.academy.converter.impl.PageableConverter;
import it.academy.converter.impl.UserConverter;
import it.academy.dto.auth.UserDto;
import it.academy.models.pageable.Pageable;
import it.academy.services.auth.IUserService;
import it.academy.services.auth.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataAuth.ATTR_LOGIN;
import static it.academy.utils.DataAuth.ATTR_SEARCH_LOGIN;
import static it.academy.utils.DataAuth.USERS_JSP;
import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataPageable.PAGEABLE;


public class ListUsers implements Command {
    private final IConverter<Pageable<UserDto>> converterP = new PageableConverter<>();

    private final IUserConverter converter = new UserConverter();

    private final IUserService service = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<UserDto> pageableDto = converterP.convertToDto(request);
        pageableDto.setSearchFields(converter.convertSearchFields(request));

        //заглушка
        //добавить возможность сортировки по полям
        pageableDto.setSortField(ATTR_ID);

        pageableDto = service.getPageableRecords(pageableDto);
        request.setAttribute(PAGEABLE, pageableDto);

        if (pageableDto.getSearchFields().containsKey(ATTR_LOGIN)) {
            request.setAttribute(ATTR_SEARCH_LOGIN,
                    pageableDto.getSearchFields().get(ATTR_LOGIN));
        }

        return USERS_JSP;
    }
}
