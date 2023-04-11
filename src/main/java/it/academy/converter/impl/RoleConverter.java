package it.academy.converter.impl;

import it.academy.converter.IRoleConverter;
import it.academy.dto.auth.RoleDto;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static it.academy.utils.DataAuth.ATTR_ROLE_NAME;
import static it.academy.utils.DataAuth.ATTR_SEARCH_ROLE_NAME;
import static it.academy.utils.DataGeneral.ATTR_ID;

public class RoleConverter implements IRoleConverter {
    @Override
    public RoleDto convertToDto(HttpServletRequest request) {
        String id = request.getParameter(ATTR_ID);
        String roleName = request.getParameter(ATTR_ROLE_NAME);

        return RoleDto.builder()
                .id(StringUtils.isBlank(id) ? null : Integer.parseInt(id))
                .roleName(roleName)
                .build();
    }

    @Override
    public HashMap<String, Object> convertSearchFields(HttpServletRequest request) {
        HashMap<String, Object> searchFields = new HashMap<>();

        String roleName = request.getParameter(ATTR_SEARCH_ROLE_NAME);
        searchFields.put(ATTR_ROLE_NAME, roleName);

        return searchFields;
    }
}
