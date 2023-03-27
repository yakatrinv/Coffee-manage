package it.academy.converter;

import it.academy.dto.ProductDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface IProductConverter extends IConverter<ProductDto> {
    HashMap<String, Object> convertSearchFields(HttpServletRequest request);
}
