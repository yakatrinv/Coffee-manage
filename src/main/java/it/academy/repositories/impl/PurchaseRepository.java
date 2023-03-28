package it.academy.repositories.impl;

import it.academy.models.Purchase;
import it.academy.repositories.IPurchaseRepository;

import static it.academy.utils.Data.PURCHASE_CLASS;

public class PurchaseRepository extends CrudRepository<Purchase>
        implements IPurchaseRepository {
    public PurchaseRepository() {
        super(PURCHASE_CLASS);
    }
}
