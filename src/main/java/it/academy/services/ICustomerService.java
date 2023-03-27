package it.academy.services;

import it.academy.dto.CustomerDto;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;

public interface ICustomerService {
    CustomerDto createCustomer(String login, String password,CustomerDto customerDto);

    CustomerDto updateCustomer(CustomerDto customerDto);

    CustomerDto findCustomerById(Serializable id);

    void deleteCustomerById(Serializable id);

    Pageable<CustomerDto> getPageableRecords(Pageable<CustomerDto> pageable);
}
