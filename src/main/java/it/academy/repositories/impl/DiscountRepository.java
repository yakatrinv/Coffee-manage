package it.academy.repositories.impl;

import it.academy.models.Discount;
import it.academy.repositories.IDiscountRepository;
import it.academy.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static it.academy.utils.Data.ATTR_PERCENT;
import static it.academy.utils.Data.ATTR_SUM;
import static it.academy.utils.Data.DISCOUNT_CLASS;
import static it.academy.utils.Data.FIRST_INDEX;

public class DiscountRepository extends CrudRepository<Discount>
        implements IDiscountRepository {
    private EntityManager entityManager;

    public DiscountRepository() {
        super(DISCOUNT_CLASS);
    }

    @Override
    public Discount getPercentDiscount(Float sum) {
        Discount discount = null;
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder cbDiscount =
                    entityManager.getCriteriaBuilder();
            CriteriaQuery<Discount> queryDiscount =
                    cbDiscount.createQuery(DISCOUNT_CLASS);

            Root<Discount> root = queryDiscount.from(DISCOUNT_CLASS);
            queryDiscount.where(cbDiscount.le(root.get(ATTR_SUM), sum))
                    .orderBy(cbDiscount.asc(root.get(ATTR_PERCENT)));

            List<Discount> discounts = entityManager
                    .createQuery(queryDiscount)
                    .getResultList();

            if (discounts != null) {
                discount = discounts.isEmpty() ? null : discounts.get(FIRST_INDEX);
            }

            entityManager.getTransaction().commit();
        } catch (
                Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        return discount;
    }
}
