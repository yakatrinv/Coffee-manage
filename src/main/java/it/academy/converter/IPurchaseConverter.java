package it.academy.converter;

import it.academy.dto.PurchaseDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface IPurchaseConverter extends IConverter<PurchaseDto> {
    HashMap<String, Object> convertSearchFields(HttpServletRequest request);
}
