package it.academy.mappers.impl;

import it.academy.dto.DiscountDto;
import it.academy.mappers.Mapper;
import it.academy.models.Discount;

public class DiscountMapper implements Mapper<Discount, DiscountDto> {
    @Override
    public Discount dtoToEntity(DiscountDto discountDto) {
        return Discount.builder()
                .id(discountDto.getId())
                .sum(discountDto.getSum())
                .percent(discountDto.getPercent())
                .build();
    }

    @Override
    public DiscountDto entityToDto(Discount discount) {
        return DiscountDto.builder()
                .id(discount.getId())
                .sum(discount.getSum())
                .percent(discount.getPercent())
                .build();
    }
}
