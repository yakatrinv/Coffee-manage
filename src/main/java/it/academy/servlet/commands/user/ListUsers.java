package it.academy.servlet.commands.user;

import it.academy.converter.IConverter;
import it.academy.converter.IUserConverter;
import it.academy.converter.impl.PageableConverter;
import it.academy.converter.impl.UserConverter;
import it.academy.dto.auth.UserAuthDto;
import it.academy.models.pageable.Pageable;
import it.academy.services.auth.IUserAuthService;
import it.academy.services.auth.UserAuthService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_LOGIN;
import static it.academy.utils.Data.ATTR_SEARCH_LOGIN;
import static it.academy.utils.Data.PAGEABLE;
import static it.academy.utils.Data.USERS_JSP;


public class ListUsers implements Command {
    private final IConverter<Pageable<UserAuthDto>> converter = new PageableConverter<>();

    private final IUserConverter userConverter = new UserConverter();

    private final IUserAuthService userService = new UserAuthService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<UserAuthDto> pageableDto = converter.convertToDto(request);
        pageableDto.setSearchFields(userConverter.convertSearchFields(request));

        //заглушка
        //добавить возможность сортировки по полям
        pageableDto.setSortField(ATTR_ID);

        pageableDto = userService.getPageableRecords(pageableDto);
        request.setAttribute(PAGEABLE, pageableDto);

        if (pageableDto.getSearchFields().containsKey(ATTR_LOGIN)) {
            request.setAttribute(ATTR_SEARCH_LOGIN,
                    pageableDto.getSearchFields().get(ATTR_LOGIN));
        }

        return USERS_JSP;
    }
}
