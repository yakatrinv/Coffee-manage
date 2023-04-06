package it.academy.repositories.impl;

import it.academy.models.CreditCard;
import it.academy.models.Customer;
import it.academy.repositories.ICreditCardRepository;
import it.academy.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

import static it.academy.utils.DataCustomer.ATTR_CUSTOMER;
import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataGeneral.CREDIT_CARD_CLASS;

public class CreditCardRepository extends CrudRepository<CreditCard>
        implements ICreditCardRepository {
    private EntityManager entityManager;

    public CreditCardRepository() {
        super(CREDIT_CARD_CLASS);
    }

    @Override
    public List<CreditCard> getCreditCards(Serializable id) {
        List<CreditCard> resultList = null;
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder =
                    entityManager.getCriteriaBuilder();

            CriteriaQuery<CreditCard> query = criteriaBuilder.createQuery(CREDIT_CARD_CLASS);
            Root<CreditCard> root = query.from(CREDIT_CARD_CLASS);

            Join<CreditCard, Customer> creditCardJoin = root.join(ATTR_CUSTOMER);
            query.where(criteriaBuilder.equal(creditCardJoin.get(ATTR_ID), id));

            TypedQuery<CreditCard> resultQuery = entityManager.createQuery(query);

            resultList = resultQuery.getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        return resultList;
    }
}
