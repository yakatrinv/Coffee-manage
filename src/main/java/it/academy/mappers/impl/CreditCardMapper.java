package it.academy.mappers.impl;

import it.academy.dto.CreditCardDto;
import it.academy.dto.CustomerDto;
import it.academy.mappers.Mapper;
import it.academy.models.CreditCard;
import it.academy.models.Customer;

public class CreditCardMapper implements Mapper<CreditCard, CreditCardDto> {
    private final Mapper<Customer, CustomerDto> customerMapper = new CustomerMapper();

    @Override
    public CreditCard dtoToEntity(CreditCardDto creditCardDto) {
        Customer customer =
                creditCardDto.getCustomer() == null ? null :
                        customerMapper.dtoToEntity(creditCardDto.getCustomer());

        return CreditCard.builder()
                .id(creditCardDto.getId())
                .number(creditCardDto.getNumber())
                .customer(customer)
                .build();
    }

    @Override
    public CreditCardDto entityToDto(CreditCard creditCard) {
        CustomerDto customer =
                creditCard.getCustomer() == null ? null :
                        customerMapper.entityToDto(creditCard.getCustomer());

        return CreditCardDto.builder()
                .id(creditCard.getId())
                .number(creditCard.getNumber())
                .customer(customer)
                .build();
    }
}
