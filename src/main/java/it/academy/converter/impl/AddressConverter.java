package it.academy.converter.impl;

import it.academy.converter.IAddressConverter;
import it.academy.dto.AddressDto;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static it.academy.utils.DataAddress.ATTR_CITY;
import static it.academy.utils.DataAddress.ATTR_SEARCH_CITY;
import static it.academy.utils.DataAddress.ATTR_SEARCH_STREET;
import static it.academy.utils.DataAddress.ATTR_STREET;
import static it.academy.utils.DataGeneral.ATTR_ID;

public class AddressConverter implements IAddressConverter {
    @Override
    public AddressDto convertToDto(HttpServletRequest request) {
        String id = request.getParameter(ATTR_ID);
        String city = request.getParameter(ATTR_CITY);
        String street = request.getParameter(ATTR_STREET);

        return AddressDto.builder()
                .id(StringUtils.isBlank(id) ? null : Integer.parseInt(id))
                .city(city)
                .street(street)
                .build();
    }

    @Override
    public HashMap<String, Object> convertSearchFields(HttpServletRequest request) {
        HashMap<String, Object> searchFields = new HashMap<>();

        String city = request.getParameter(ATTR_SEARCH_CITY);
        String street = request.getParameter(ATTR_SEARCH_STREET);

        searchFields.put(ATTR_CITY, city);
        searchFields.put(ATTR_STREET, street);

        return searchFields;
    }
}
