package it.academy.converter.impl;

import it.academy.converter.IAddressConverter;
import it.academy.dto.AddressDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static it.academy.utils.Data.ATTR_CITY;
import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_SEARCH_CITY;
import static it.academy.utils.Data.ATTR_SEARCH_STREET;
import static it.academy.utils.Data.ATTR_STREET;

public class AddressConverter implements IAddressConverter {
    @Override
    public AddressDto convertToDto(HttpServletRequest request) {
        String id = request.getParameter(ATTR_ID);
        String city = request.getParameter(ATTR_CITY);
        String street = request.getParameter(ATTR_STREET);

        return AddressDto.builder()
                .id(isEmpty(id) ? null : Integer.valueOf(id))
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

    private boolean isEmpty(String value) {
        return (value == null || value.isEmpty());
    }
}
