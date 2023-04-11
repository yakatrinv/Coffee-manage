package it.academy.converter;

import it.academy.dto.ProductDto;

import javax.servlet.http.HttpServletRequest;

public interface IProductConverter extends IConverter<ProductDto> {
    String convertSortFields(HttpServletRequest request);
}
