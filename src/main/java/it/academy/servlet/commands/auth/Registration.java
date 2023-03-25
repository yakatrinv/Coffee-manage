package it.academy.servlet.commands.auth;

import it.academy.converter.IConverter;
import it.academy.converter.impl.auth.UserConverter;
import it.academy.dto.auth.UserDto;
import it.academy.services.auth.IUserService;
import it.academy.services.auth.UserService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static it.academy.utils.Data.LOGIN_JSP;

public class Registration implements Command {
    private final IConverter<UserDto> converter = new UserConverter();
    private final IUserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, InvalidKeySpecException {
        UserDto userDto = converter.convertToDto(request);
        userService.createUser(userDto);
        return LOGIN_JSP;
    }
}
