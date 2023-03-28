package it.academy.services;

import it.academy.dto.DiscountDto;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IDiscountService {
    DiscountDto createDiscount(DiscountDto discountDto);

    DiscountDto updateDiscount(DiscountDto discountDto);

    DiscountDto findDiscountById(Serializable id);

    void deleteDiscountById(Serializable id);

    Pageable<DiscountDto> getPageableRecords(Pageable<DiscountDto> pageable);

    List<DiscountDto> findAllDiscounts();
}
