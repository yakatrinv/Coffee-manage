package it.academy.converter.impl.auth;

import it.academy.converter.IConverter;
import it.academy.dto.auth.UserDto;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Data.ATTR_LOGIN;
import static it.academy.utils.Data.ATTR_PASSWORD;

public class UserConverter implements IConverter<UserDto> {
    @Override
    public UserDto convertToDto(HttpServletRequest request) {
        String login = request.getParameter(ATTR_LOGIN);
        String password = request.getParameter(ATTR_PASSWORD);

        return UserDto.builder()
                .login(login)
                .password(password)
                .build();
    }
}
