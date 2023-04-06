package it.academy.mappers.impl;

import it.academy.dto.CreditCardDto;
import it.academy.dto.CustomerDto;
import it.academy.mappers.Mapper;
import it.academy.models.CreditCard;
import it.academy.models.Customer;

import java.util.Set;
import java.util.stream.Collectors;

public class CustomerMapper implements Mapper<Customer, CustomerDto> {
    Mapper<CreditCard, CreditCardDto> mapper = new CreditCardMapper();

    @Override
    public Customer dtoToEntity(CustomerDto customerDto) {
//        Set<CreditCard> creditCards =
//                customerDto.getCreditCards() == null ? null :
//                        customerDto.getCreditCards()
//                                .stream()
//                                .map(mapper::dtoToEntity)
//                                .collect(Collectors.toSet());

        return Customer.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .phone(customerDto.getPhone())
                .email(customerDto.getEmail())
//                .creditCards(creditCards)
                .build();
    }

    @Override
    public CustomerDto entityToDto(Customer customer) {
//        Set<CreditCardDto> creditCards =
//                customer.getCreditCards() == null ? null :
//                        customer.getCreditCards()
//                                .stream()
//                                .map(mapper::entityToDto)
//                                .collect(Collectors.toSet());


        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .phone(customer.getPhone())
                .email(customer.getEmail())
//                .creditCards(creditCards)
                .build();
    }
}
