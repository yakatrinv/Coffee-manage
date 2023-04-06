package it.academy.converter.impl;

import it.academy.converter.IUserConverter;
import it.academy.dto.auth.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static it.academy.utils.DataAuth.ATTR_LOGIN;
import static it.academy.utils.DataAuth.ATTR_SEARCH_LOGIN;

public class UserConverter implements IUserConverter {
    @Override
    public UserDto convertToDto(HttpServletRequest request) {
        String login = request.getParameter(ATTR_LOGIN);

        return UserDto.builder()
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
