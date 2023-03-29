package it.academy.converter.impl;

import it.academy.converter.IDiscountConverter;
import it.academy.dto.DiscountDto;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_PERCENT;
import static it.academy.utils.Data.ATTR_SEARCH_PERCENT;
import static it.academy.utils.Data.ATTR_SEARCH_SUM;
import static it.academy.utils.Data.ATTR_SUM;
import static it.academy.utils.Data.VALUE_ZERO;

public class DiscountConverter implements IDiscountConverter {
    @Override
    public DiscountDto convertToDto(HttpServletRequest request) {
        String id = request.getParameter(ATTR_ID);
        String sum = request.getParameter(ATTR_SUM);
        String percent = request.getParameter(ATTR_PERCENT);

        return DiscountDto.builder()
                .id(StringUtils.isBlank(id) ? null : Integer.parseInt(id))
                .sum(StringUtils.isBlank(sum) ? VALUE_ZERO : Integer.parseInt(sum))
                .percent(StringUtils.isBlank(percent) ? VALUE_ZERO : Integer.parseInt(percent))
                .build();
    }

    @Override
    public HashMap<String, Object> convertSearchFields(HttpServletRequest request) {
        HashMap<String, Object> searchFields = new HashMap<>();

        String sum = request.getParameter(ATTR_SEARCH_SUM);
        String percent = request.getParameter(ATTR_SEARCH_PERCENT);

        searchFields.put(ATTR_SUM, StringUtils.isBlank(sum) ? null : Integer.parseInt(sum));
        searchFields.put(ATTR_PERCENT, StringUtils.isBlank(percent) ? null : Integer.parseInt(percent));

        return searchFields;
    }
}
