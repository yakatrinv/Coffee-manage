package it.academy.repositories.impl;

import it.academy.models.Customer;
import it.academy.models.Purchase;
import it.academy.repositories.IPurchaseRepository;
import it.academy.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.io.Serializable;

import static it.academy.utils.Data.ATTR_CUSTOMER;
import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_SUM;
import static it.academy.utils.Data.PURCHASE_CLASS;

public class PurchaseRepository extends CrudRepository<Purchase>
        implements IPurchaseRepository {
    private EntityManager entityManager;

    public PurchaseRepository() {
        super(PURCHASE_CLASS);
    }

    @Override
    public Float getSumPurchases(Serializable id) {
        Float sum = 0f;
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder cbSum =
                    entityManager.getCriteriaBuilder();
            CriteriaQuery<Float> querySum =
                    cbSum.createQuery(Float.class);

            Root<Purchase> root = querySum.from(PURCHASE_CLASS);
            Join<Purchase, Customer> customerJoin = root.join(ATTR_CUSTOMER);
            querySum.select(cbSum.sum(root.get(ATTR_SUM))).where(cbSum.equal(customerJoin.get(ATTR_ID), id));

            sum = entityManager
                    .createQuery(querySum)
                    .getSingleResult();

            entityManager.getTransaction().commit();
        } catch (
                Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        return sum;
    }
}
