package it.academy.repositories.impl;

import it.academy.models.CreditCard;
import it.academy.models.Customer;
import it.academy.models.auth.User;
import it.academy.repositories.ICustomerRepository;
import it.academy.repositories.auth.IUserRepository;
import it.academy.repositories.auth.UserRepository;
import it.academy.utils.HibernateUtil;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

import static it.academy.utils.Data.ATTR_CUSTOMER_ID;
import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_USER;
import static it.academy.utils.Data.CREDIT_CARD_CLASS;
import static it.academy.utils.Data.CUSTOMER_CLASS;
import static it.academy.utils.Data.FIRST_INDEX;

public class CustomerRepository extends CrudRepository<Customer>
        implements ICustomerRepository {
    private EntityManager entityManager;

    private final IUserRepository userRepository = new UserRepository();

    public CustomerRepository() {
        super(CUSTOMER_CLASS);
    }

    @Override
    public Customer getCustomerByLoginUser(String login) {
        User user = userRepository.findByLogin(login);
        Customer customer = null;

        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Customer> query = builder.createQuery(CUSTOMER_CLASS);
            Root<Customer> userRoot = query.from(CUSTOMER_CLASS);

            Join<Customer, User> customerUserJoin = userRoot.join(ATTR_USER);
            query.where(builder.equal(customerUserJoin.get(ATTR_ID), user.getId()));

            List<Customer> resultList = entityManager
                    .createQuery(query)
                    .getResultList();
            if (CollectionUtils.isNotEmpty(resultList)) {
                customer = resultList.get(FIRST_INDEX);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        return customer;
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

            Join<CreditCard, Customer> creditCardJoin = root.join(ATTR_CUSTOMER_ID);
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
