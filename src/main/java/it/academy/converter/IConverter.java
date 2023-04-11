package it.academy.converter;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface IConverter<T> {
    T convertToDto(HttpServletRequest request);

    HashMap<String, Object> convertSearchFields(HttpServletRequest request);
}
