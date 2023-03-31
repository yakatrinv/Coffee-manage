package it.academy.repositories;

import it.academy.models.Customer;

public interface ICustomerRepository extends ICrudRepository<Customer> {
    Customer getCustomerByLoginUser(String login);
}
