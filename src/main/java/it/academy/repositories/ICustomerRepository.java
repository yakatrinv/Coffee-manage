package it.academy.repositories;

import it.academy.models.CreditCard;
import it.academy.models.Customer;

import java.io.Serializable;
import java.util.List;

public interface ICustomerRepository extends ICrudRepository<Customer> {
    Customer getCustomerByLoginUser(String login);

    List<CreditCard> getCreditCards(Serializable id);
}
