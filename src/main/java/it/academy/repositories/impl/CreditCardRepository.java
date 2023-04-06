package it.academy.repositories.impl;

import it.academy.models.CreditCard;
import it.academy.repositories.ICreditCardRepository;

import static it.academy.utils.Data.CREDIT_CARD_CLASS;

public class CreditCardRepository extends CrudRepository<CreditCard>
        implements ICreditCardRepository {
    public CreditCardRepository() {
        super(CREDIT_CARD_CLASS);
    }
}
