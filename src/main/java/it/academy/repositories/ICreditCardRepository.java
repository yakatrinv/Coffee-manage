package it.academy.repositories;

import it.academy.models.CreditCard;

import java.io.Serializable;
import java.util.List;

public interface ICreditCardRepository extends ICrudRepository<CreditCard> {
    List<CreditCard> getCreditCards(Serializable id);
}
