package it.academy.repositories.impl;

import it.academy.models.Address;
import it.academy.repositories.IAddressRepository;
import it.academy.services.Pageable;
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
import static it.academy.utils.Data.LONG_CLASS;
import static it.academy.utils.Data.PERCENT_STRING;
import static it.academy.utils.DataUI.AND_FOR_PARAM;
import static it.academy.utils.DataUI.EQUALS;
import static it.academy.utils.DataUI.FIRST_PAGE;
import static it.academy.utils.Utils.isEmpty;

public class AddressRepository extends CrudRepository<Address>
        implements IAddressRepository {
    private EntityManager entityManager;

    public AddressRepository() {
        super(ADDRESS_CLASS);
    }

    @Override
    public Pageable<Address> fillPageable(Pageable<Address> pageable) {
        getCountRecords(pageable);
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder =
                    entityManager.getCriteriaBuilder();
            CriteriaQuery<Address> query = criteriaBuilder.createQuery(ADDRESS_CLASS);
            Root<Address> root = query.from(ADDRESS_CLASS);
            List<Predicate> predicates = new ArrayList<>();

            //filtered
            StringBuilder paramPage = new StringBuilder();
            HashMap<String, Object> filteredFields
                    = pageable.getFilteredFields();
            for (Map.Entry<String, Object> pair : filteredFields.entrySet()) {
                String key = pair.getKey();
                Object value = pair.getValue();

                if (!isEmpty(key) && value != null) {
                    if (value.getClass() == String.class) {
                        if (!((String) value).isEmpty()) {
                            predicates.add(criteriaBuilder
                                    .like(root.get(key), value + PERCENT_STRING));

                            paramPage.append(AND_FOR_PARAM)
                                    .append(key)
                                    .append(EQUALS).append(value);
                        }
                    } else {
                        predicates.add(criteriaBuilder
                                .equal(root.get(key), value));

                        paramPage.append(AND_FOR_PARAM)
                                .append(key)
                                .append(EQUALS).append(value);
                    }
                }
            }

            Predicate[] predicatesArray = predicates.toArray(new Predicate[0]);
            if (predicatesArray.length > 0) {
                Predicate predicate =
                        criteriaBuilder.and(predicatesArray);
                query.where(predicate);
            }

            //order
            String sortField = pageable.getSortField();
            if (!isEmpty(sortField)) {
                query.orderBy(criteriaBuilder.asc(root.get(sortField)));
            } else {
                query.orderBy(criteriaBuilder.asc(root.get("id")));
            }

            pageable.setParamPage(String.valueOf(paramPage));

            TypedQuery<Address> resultQuery = entityManager.createQuery(query);
            resultQuery.setFirstResult(pageable.getOffset());
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
                    = pageable.getFilteredFields();
            for (Map.Entry<String, Object> pair : filteredFields.entrySet()) {
                String key = pair.getKey();
                Object value = pair.getValue();

                if (!isEmpty(key) && value != null) {
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

            int lastPage = getPages(countRecords, pageable.getPageSize());
            pageable.setTotalRecords(countRecords);
            pageable.setLastPageNumber(lastPage);

            if (pageable.getPageNumber() > pageable.getLastPageNumber()) {
                pageable.setPageNumber(FIRST_PAGE);
            }

            int offset = (pageable.getPageNumber() - 1) *
                    pageable.getPageSize();
            pageable.setOffset(offset);

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
}
