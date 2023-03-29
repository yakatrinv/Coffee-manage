package it.academy.converter.impl;

import it.academy.converter.ICustomerConverter;
import it.academy.dto.CustomerDto;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static it.academy.utils.Data.ATTR_EMAIL;
import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_NAME;
import static it.academy.utils.Data.ATTR_PHONE;
import static it.academy.utils.Data.ATTR_SEARCH_EMAIL;
import static it.academy.utils.Data.ATTR_SEARCH_NAME;
import static it.academy.utils.Data.ATTR_SEARCH_PHONE;
import static it.academy.utils.Data.ATTR_SEARCH_SURNAME;
import static it.academy.utils.Data.ATTR_SURNAME;

public class CustomerConverter implements ICustomerConverter {
    @Override
    public CustomerDto convertToDto(HttpServletRequest request) {
        String id = request.getParameter(ATTR_ID);
        String name = request.getParameter(ATTR_NAME);
        String surname = request.getParameter(ATTR_SURNAME);
        String phone = request.getParameter(ATTR_PHONE);
        String email = request.getParameter(ATTR_EMAIL);

        return CustomerDto.builder()
                .id(StringUtils.isBlank(id) ? null : Integer.parseInt(id))
                .name(name)
                .surname(surname)
                .phone(phone)
                .email(email)
                .build();
    }

    @Override
    public HashMap<String, Object> convertSearchFields(HttpServletRequest request) {
        HashMap<String, Object> searchFields = new HashMap<>();

        String name = request.getParameter(ATTR_SEARCH_NAME);
        String surname = request.getParameter(ATTR_SEARCH_SURNAME);
        String phone = request.getParameter(ATTR_SEARCH_PHONE);
        String email = request.getParameter(ATTR_SEARCH_EMAIL);

        searchFields.put(ATTR_NAME, name);
        searchFields.put(ATTR_SURNAME, surname);
        searchFields.put(ATTR_PHONE, phone);
        searchFields.put(ATTR_EMAIL, email);

        return searchFields;
    }
}
