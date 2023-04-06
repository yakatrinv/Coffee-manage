package it.academy.services;

import it.academy.dto.DiscountDto;
import it.academy.models.Discount;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IDiscountService {
    void createDiscount(DiscountDto discountDto);

    void updateDiscount(DiscountDto discountDto);

    DiscountDto findDiscountById(Serializable id);

    void deleteDiscountById(Serializable id);

    List<DiscountDto> findAllDiscounts();

    Discount getPercentDiscount(float sum);

    Pageable<DiscountDto> getPageableRecords(Pageable<DiscountDto> pageable);
}
