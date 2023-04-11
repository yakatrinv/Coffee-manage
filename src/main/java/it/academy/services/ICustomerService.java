package it.academy.services;

import it.academy.dto.CustomerDto;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;
import java.util.List;

public interface ICustomerService {
    void createCustomer(String login, String password, CustomerDto customerDto);

    void updateCustomer(CustomerDto customerDto);

    CustomerDto findCustomerById(Serializable id);

    void deleteCustomerById(Serializable id);

    List<CustomerDto> findAllCustomers();

    CustomerDto getCustomerByLoginUser(String login);

    Pageable<CustomerDto> getPageableRecords(Pageable<CustomerDto> pageable);
}
