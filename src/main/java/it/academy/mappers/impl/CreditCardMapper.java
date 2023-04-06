package it.academy.mappers.impl;

import it.academy.dto.CreditCardDto;
import it.academy.mappers.Mapper;
import it.academy.models.CreditCard;

public class CreditCardMapper implements Mapper<CreditCard, CreditCardDto> {
    @Override
    public CreditCard dtoToEntity(CreditCardDto creditCardDto) {
        return CreditCard.builder()
                .id(creditCardDto.getId())
                .number(creditCardDto.getNumber())
                .build();
    }

    @Override
    public CreditCardDto entityToDto(CreditCard creditCard) {
        return CreditCardDto.builder()
                .id(creditCard.getId())
                .number(creditCard.getNumber())
                .build();
    }
}
