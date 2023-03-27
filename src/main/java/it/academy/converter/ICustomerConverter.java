package it.academy.converter;

import it.academy.dto.CustomerDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface ICustomerConverter extends IConverter<CustomerDto> {
    HashMap<String, Object> convertSearchFields(HttpServletRequest request);
}
