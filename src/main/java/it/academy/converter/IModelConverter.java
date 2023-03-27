package it.academy.converter;

import it.academy.dto.ModelDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface IModelConverter extends IConverter<ModelDto> {
    HashMap<String, Object> convertSearchFields(HttpServletRequest request);
}
