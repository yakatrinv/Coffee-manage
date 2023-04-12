package it.academy.converter;

import it.academy.dto.PurchaseDto;

import javax.servlet.http.HttpServletRequest;

public interface IPurchaseConverter extends IConverter<PurchaseDto> {
    String convertSortFields(HttpServletRequest request);
}
