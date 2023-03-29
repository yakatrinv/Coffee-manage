package it.academy.converter;

import it.academy.dto.auth.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface IUserConverter extends IConverter<UserDto> {
    HashMap<String, Object> convertSearchFields(HttpServletRequest request);
}
