package it.academy.converter;

import it.academy.dto.AddressDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface IAddressConverter extends IConverter<AddressDto> {
    HashMap<String, Object> convertSearchFields(HttpServletRequest request);
}
