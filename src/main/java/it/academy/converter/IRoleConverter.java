package it.academy.converter;

import it.academy.dto.auth.RoleDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface IRoleConverter extends IConverter<RoleDto> {
    HashMap<String, Object> convertSearchFields(HttpServletRequest request);
}
