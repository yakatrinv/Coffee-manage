package it.academy.repositories.impl;

import it.academy.models.Customer;
import it.academy.repositories.ICustomerRepository;

import static it.academy.utils.Data.CUSTOMER_CLASS;

public class CustomerRepository extends CrudRepository<Customer>
        implements ICustomerRepository {
    public CustomerRepository() {
        super(CUSTOMER_CLASS);
    }
}
