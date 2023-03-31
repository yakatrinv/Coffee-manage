package it.academy.repositories.impl;

import it.academy.models.Customer;
import it.academy.models.auth.User;
import it.academy.repositories.ICustomerRepository;
import it.academy.repositories.auth.IUserRepository;
import it.academy.repositories.auth.UserRepository;
import it.academy.utils.HibernateUtil;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_USER;
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
}
