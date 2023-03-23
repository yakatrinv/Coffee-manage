package it.academy.repositories.auth;

import it.academy.models.auth.User;
import it.academy.repositories.impl.CrudRepository;
import it.academy.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static it.academy.utils.Data.ATTR_LOGIN;
import static it.academy.utils.Data.ATTR_PASSWORD;
import static it.academy.utils.Data.ATTR_ROLE_ID;
import static it.academy.utils.Data.FIRST_INDEX;
import static it.academy.utils.Data.USER_CLASS;

public class UserRepository extends CrudRepository<User>
        implements IUserRepository {
    private EntityManager entityManager;

    public UserRepository() {
        super(USER_CLASS);
    }

    @Override
    public User findByLoginAndPass(String login, String password) {
        User findUser = null;
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(USER_CLASS);
            Root<User> userRoot = criteria.from(USER_CLASS);
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(builder.equal(userRoot.get(ATTR_LOGIN), login));
            predicates.add(builder.equal(userRoot.get(ATTR_PASSWORD), password));

            criteria.select(userRoot)
                    .where(predicates
                            .toArray(predicates.toArray(new Predicate[0])));

            List<User> resultList = entityManager
                    .createQuery(criteria)
                    .getResultList();
            if (!resultList.isEmpty()) {
                findUser = resultList.get(FIRST_INDEX);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return findUser;
    }


    @Override
    public User findByLoginAndRole(String login, Integer roleId) {
        User findUser = null;
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(USER_CLASS);
            Root<User> userRoot = criteria.from(USER_CLASS);
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(builder.equal(userRoot.get(ATTR_LOGIN), login));
            predicates.add(builder.equal(userRoot.get(ATTR_ROLE_ID), roleId));

            criteria.select(userRoot)
                    .where(predicates.toArray(predicates.toArray(new Predicate[0])));

            List<User> resultList = entityManager
                    .createQuery(criteria)
                    .getResultList();
            if (!resultList.isEmpty()) {
                findUser = resultList.get(FIRST_INDEX);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        return findUser;
    }
}
