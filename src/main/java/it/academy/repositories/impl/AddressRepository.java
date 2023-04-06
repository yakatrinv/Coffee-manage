package it.academy.repositories.impl;

import it.academy.models.Address;
import it.academy.repositories.IAddressRepository;
import it.academy.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static it.academy.utils.DataAddress.ATTR_CITY;
import static it.academy.utils.DataGeneral.ADDRESS_CLASS;

public class AddressRepository extends CrudRepository<Address>
        implements IAddressRepository {
    private EntityManager entityManager;

    public AddressRepository() {
        super(ADDRESS_CLASS);
    }

    @Override
    public List<String> getAllCity() {
        List<String> resultList = new ArrayList<>();
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
            Root<Address> address = query.from(ADDRESS_CLASS);
            query.select(address.get(ATTR_CITY));
            query.orderBy(criteriaBuilder.asc(address.get(ATTR_CITY)));
            query.distinct(true);

            TypedQuery<String> resultQuery = entityManager.createQuery(query);
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
