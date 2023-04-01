package it.academy.converter.impl;

import it.academy.converter.IProductConverter;
import it.academy.dto.ProductDto;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_NAME;
import static it.academy.utils.Data.ATTR_PRICE;
import static it.academy.utils.Data.ATTR_SEARCH_NAME;
import static it.academy.utils.Data.ATTR_SEARCH_PRICE;
import static it.academy.utils.DataCustomer.ATTR_SORT_FIELD;

public class ProductConverter implements IProductConverter {
    @Override
    public ProductDto convertToDto(HttpServletRequest request) {
        String id = request.getParameter(ATTR_ID);
        String name = request.getParameter(ATTR_NAME);
        String price = request.getParameter(ATTR_PRICE);

        return ProductDto.builder()
                .id(StringUtils.isBlank(id) ? null : Integer.parseInt(id))
                .name(name)
                .price(StringUtils.isBlank(price) ? 0 : Float.parseFloat(price))
                .build();
    }

    @Override
    public HashMap<String, Object> convertSearchFields(HttpServletRequest request) {
        HashMap<String, Object> searchFields = new HashMap<>();

        String name = request.getParameter(ATTR_SEARCH_NAME);
        String price = request.getParameter(ATTR_SEARCH_PRICE);

        searchFields.put(ATTR_NAME, name);
        searchFields.put(ATTR_PRICE, StringUtils.isBlank(price) ? 0 : Float.parseFloat(price));

        return searchFields;
    }

    @Override
    public String convertSortFields(HttpServletRequest request) {
        String sortField = request.getParameter(ATTR_SORT_FIELD);
        return StringUtils.isBlank(sortField) ? ATTR_ID : sortField;
    }
}
