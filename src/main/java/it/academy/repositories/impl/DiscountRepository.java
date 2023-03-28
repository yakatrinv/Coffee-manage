package it.academy.repositories.impl;

import it.academy.models.Discount;
import it.academy.repositories.IDiscountRepository;

import static it.academy.utils.Data.DISCOUNT_CLASS;

public class DiscountRepository extends CrudRepository<Discount>
        implements IDiscountRepository {
    public DiscountRepository() {
        super(DISCOUNT_CLASS);
    }
}
