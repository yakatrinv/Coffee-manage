package it.academy.converter;

import it.academy.dto.auth.UserAuthDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface IUserConverter extends IConverter<UserAuthDto> {
    HashMap<String, Object> convertSearchFields(HttpServletRequest request);
}
