package it.academy.converter.impl;

import it.academy.converter.IUserConverter;
import it.academy.dto.auth.UserAuthDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static it.academy.utils.Data.ATTR_LOGIN;
import static it.academy.utils.Data.ATTR_SEARCH_LOGIN;

public class UserConverter implements IUserConverter {
    @Override
    public UserAuthDto convertToDto(HttpServletRequest request) {
        String login = request.getParameter(ATTR_LOGIN);

        return UserAuthDto.builder()
                .login(login)
                .build();
    }

    @Override
    public HashMap<String, Object> convertSearchFields(HttpServletRequest request) {
        HashMap<String, Object> searchFields = new HashMap<>();

        String login = request.getParameter(ATTR_SEARCH_LOGIN);

        searchFields.put(ATTR_LOGIN, login);

        return searchFields;
    }
}
