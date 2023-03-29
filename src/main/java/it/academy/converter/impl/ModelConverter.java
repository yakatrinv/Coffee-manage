package it.academy.converter.impl;

import it.academy.converter.IModelConverter;
import it.academy.dto.ModelDto;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static it.academy.utils.Data.ATTR_BRAND;
import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_NAME_MODEL;
import static it.academy.utils.Data.ATTR_SEARCH_BRAND;
import static it.academy.utils.Data.ATTR_SEARCH_NAME_MODEL;

public class ModelConverter implements IModelConverter {
    @Override
    public ModelDto convertToDto(HttpServletRequest request) {
        String id = request.getParameter(ATTR_ID);
        String brand = request.getParameter(ATTR_BRAND);
        String nameModel = request.getParameter(ATTR_NAME_MODEL);

        return ModelDto.builder()
                .id(StringUtils.isBlank(id) ? null : Integer.parseInt(id))
                .brand(brand)
                .nameModel(nameModel)
                .build();
    }

    @Override
    public HashMap<String, Object> convertSearchFields(HttpServletRequest request) {
        HashMap<String, Object> searchFields = new HashMap<>();

        String brand = request.getParameter(ATTR_SEARCH_BRAND);
        String nameModel = request.getParameter(ATTR_SEARCH_NAME_MODEL);

        searchFields.put(ATTR_BRAND, brand);
        searchFields.put(ATTR_NAME_MODEL, nameModel);

        return searchFields;
    }
}
