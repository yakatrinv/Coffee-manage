package it.academy.converter.impl;

import it.academy.converter.IConverter;
import it.academy.models.pageable.Pageable;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static it.academy.utils.DataPageable.DEFAULT_PAGE_SIZE;
import static it.academy.utils.DataPageable.FIRST_PAGE;
import static it.academy.utils.DataPageable.PAGE_NUMBER;
import static it.academy.utils.DataPageable.PAGE_SIZE;

public class PageableConverter<TDto> implements IConverter<Pageable<TDto>> {
    @Override
    public Pageable<TDto> convertToDto(HttpServletRequest request) {
        String pageSize = request.getParameter(PAGE_SIZE);
        String pageNumber = request.getParameter(PAGE_NUMBER);
        return Pageable.<TDto>builder()
                .pageSize(StringUtils.isBlank(pageSize) ? DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize))
                .pageNumber(StringUtils.isBlank(pageNumber) ? FIRST_PAGE : Integer.parseInt(pageNumber))
                .build();
    }

    @Override
    public HashMap<String, Object> convertSearchFields(HttpServletRequest request) {
        return null;
    }
}
