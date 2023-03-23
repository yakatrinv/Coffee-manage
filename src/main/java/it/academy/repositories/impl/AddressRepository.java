package it.academy.repositories.impl;

import it.academy.models.Address;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IAddressRepository;
import it.academy.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.academy.utils.Data.ADDRESS_CLASS;
import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.LONG_CLASS;
import static it.academy.utils.Data.PERCENT_STRING;

public class AddressRepository extends CrudRepository<Address>
        implements IAddressRepository {
    private EntityManager entityManager;

    public AddressRepository() {
        super(ADDRESS_CLASS);
    }

    @Override
    public Pageable<Address> getPageableRecords(Pageable<Address> pageable) {
        //count records
        Long countRecords = getCountRecords(pageable);
        int lastPage = getPages(countRecords, pageable.getPageSize());
        pageable.setLastPageNumber(lastPage);

        if (pageable.getPageNumber() > lastPage) {
            pageable.setPageNumber(lastPage);
        }

        //data for pageable
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder =
                    entityManager.getCriteriaBuilder();
            CriteriaQuery<Address> query = criteriaBuilder.createQuery(ADDRESS_CLASS);
            Root<Address> root = query.from(ADDRESS_CLASS);
            List<Predicate> predicates = new ArrayList<>();

            //filtered
            HashMap<String, Object> filteredFields
                    = pageable.getSearchFields();
            for (Map.Entry<String, Object> pair : filteredFields.entrySet()) {
                String key = pair.getKey();
                Object value = pair.getValue();

                if (isNotEmpty(key) && value != null) {
                    if (value.getClass() == String.class) {
                        if (!((String) value).isEmpty()) {
                            predicates.add(criteriaBuilder
                                    .like(root.get(key), value + PERCENT_STRING));
                        }
                    } else {
                        predicates.add(criteriaBuilder
                                .equal(root.get(key), value));
                    }
                }
            }

            Predicate[] predicatesArray = predicates.toArray(new Predicate[0]);
            if (predicatesArray.length > 0) {
                Predicate predicate =
                        criteriaBuilder.and(predicatesArray);
                query.where(predicate);
            }

            //order by
            String sortField = pageable.getSortField();
            if (isNotEmpty(sortField)) {
                query.orderBy(criteriaBuilder.asc(root.get(sortField)));
            } else {
                query.orderBy(criteriaBuilder.asc(root.get(ATTR_ID)));
            }

            int offset = (pageable.getPageNumber() - 1) *
                    pageable.getPageSize();
            TypedQuery<Address> resultQuery = entityManager.createQuery(query);
            resultQuery.setFirstResult(offset);
            resultQuery.setMaxResults(pageable.getPageSize());

            pageable.setRecords(resultQuery.getResultList());

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        return pageable;
    }

    @Override
    public Long getCountRecords(Pageable<Address> pageable) {
        Long countRecords = null;
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            List<Predicate> predicates = new ArrayList<>();

            CriteriaBuilder cbCount =
                    entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> queryCount =
                    cbCount.createQuery(LONG_CLASS);
            Root<Address> rootCount = queryCount.from(ADDRESS_CLASS);

            //filtered
            HashMap<String, Object> filteredFields
                    = pageable.getSearchFields();
            for (Map.Entry<String, Object> pair : filteredFields.entrySet()) {
                String key = pair.getKey();
                Object value = pair.getValue();

                if (isNotEmpty(key) && value != null) {
                    if (value.getClass() == String.class) {
                        if (!((String) value).isEmpty()) {
                            predicates.add(cbCount
                                    .like(rootCount.get(key), value + PERCENT_STRING));
                        }
                    } else {
                        predicates.add(cbCount
                                .equal(rootCount.get(key), value));
                    }
                }
            }

            Predicate[] predicatesArray = predicates.toArray(new Predicate[0]);
            if (predicatesArray.length > 0) {
                queryCount.select(cbCount.count(rootCount))
                        .where(predicatesArray);
            } else {
                queryCount.select(cbCount.count(rootCount));
            }

            //count records and pages
            countRecords = entityManager
                    .createQuery(queryCount)
                    .getSingleResult();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        return countRecords;
    }

    private int getPages(Long countRecords, int pageSize) {
        long pages = countRecords / pageSize;
        return (int) ((countRecords % pageSize == 0) ? pages : pages + 1);
    }

    private boolean isNotEmpty(String value) {
        return (value != null && !value.isEmpty());
    }
}
