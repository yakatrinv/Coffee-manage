package it.academy.converter;

import it.academy.dto.DiscountDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface IDiscountConverter extends IConverter<DiscountDto> {
    HashMap<String, Object> convertSearchFields(HttpServletRequest request);
}
