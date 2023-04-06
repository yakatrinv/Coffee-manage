package it.academy.repositories;

import it.academy.models.Purchase;

import java.io.Serializable;

public interface IPurchaseRepository extends ICrudRepository<Purchase> {
    Float getSumPurchases(Serializable id);
}
