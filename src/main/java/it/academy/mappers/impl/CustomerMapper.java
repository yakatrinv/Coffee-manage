package it.academy.mappers.impl;

import it.academy.dto.CustomerDto;
import it.academy.mappers.Mapper;
import it.academy.models.Customer;

public class CustomerMapper implements Mapper<Customer, CustomerDto> {
    @Override
    public Customer dtoToEntity(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .phone(customerDto.getPhone())
                .email(customerDto.getEmail())
                .build();
    }

    @Override
    public CustomerDto entityToDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .build();
    }
}
