package it.academy.repositories.impl;

import it.academy.models.Customer;
import it.academy.models.Purchase;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IPurchaseRepository;
import it.academy.utils.HibernateUtil;
import org.junit.platform.commons.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.academy.utils.DataCustomer.ATTR_CUSTOMER;
import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataGeneral.DATE_ARRAY_CLASS;
import static it.academy.utils.DataGeneral.FLOAT_ARRAY_CLASS;
import static it.academy.utils.DataGeneral.FLOAT_CLASS;
import static it.academy.utils.DataGeneral.LONG_CLASS;
import static it.academy.utils.DataGeneral.PERCENT_STRING;
import static it.academy.utils.DataGeneral.PURCHASE_CLASS;
import static it.academy.utils.DataGeneral.STRING_CLASS;
import static it.academy.utils.DataPurchase.ATTR_SUM;

public class PurchaseRepository extends CrudRepository<Purchase>
        implements IPurchaseRepository {
    private EntityManager entityManager;

    public final Class<Purchase> cls = PURCHASE_CLASS;

    public PurchaseRepository() {
        super(PURCHASE_CLASS);
    }

    @Override
    public float getSumPurchases(Serializable id) {
        float sum = 0f;
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder cbSum =
                    entityManager.getCriteriaBuilder();
            CriteriaQuery<Float> querySum =
                    cbSum.createQuery(FLOAT_CLASS);

            Root<Purchase> root = querySum.from(PURCHASE_CLASS);
            Join<Purchase, Customer> customerJoin = root.join(ATTR_CUSTOMER);
            querySum.select(cbSum.sum(root.get(ATTR_SUM)))
                    .where(cbSum.equal(customerJoin.get(ATTR_ID), id));

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

    @Override
    public List<Predicate> fillFilter(Pageable<Purchase> pageable, CriteriaBuilder criteriaBuilder, Root<Purchase> root) {
        List<Predicate> predicates = new ArrayList<>();

        HashMap<String, Object> filteredFields
                = pageable.getSearchFields();
        if (filteredFields != null) {
            for (Map.Entry<String, Object> pair : filteredFields.entrySet()) {
                String key = pair.getKey();
                Object value = pair.getValue();

                if (StringUtils.isNotBlank(key) && value != null) {
                    if (value.getClass() == STRING_CLASS) {
                        if (StringUtils.isNotBlank((String) value)) {
                            predicates.add(criteriaBuilder
                                    .like(root.get(key),
                                            PERCENT_STRING + value +
                                                    PERCENT_STRING));
                        }
                    } else if (value.getClass() == FLOAT_CLASS) {
                        if ((Float) value != 0) {
                            predicates.add(criteriaBuilder
                                    .equal(root.get(key), value));
                        }
                    } else if (value.getClass() == DATE_ARRAY_CLASS) {
                        if (value instanceof Object[]) {
                            predicates.add(criteriaBuilder
                                    .between(root.get(key), ((LocalDateTime[]) value)[0], ((LocalDateTime[]) value)[1]));
                        }
                    } else if (value.getClass() == FLOAT_ARRAY_CLASS) {
                        if (value instanceof Object[]) {
                            Float minSum = ((Float[]) value)[0];
                            Float maxSum = ((Float[]) value)[1];
                            if (maxSum != 0 && minSum != 0) {
                                predicates.add(criteriaBuilder.between(root.get(key), minSum, maxSum));
                            } else if (minSum == 0 && maxSum != 0) {
                                predicates.add(criteriaBuilder.le(root.get(key), maxSum));
                            } else if (minSum != 0) {
                                predicates.add(criteriaBuilder.ge(root.get(key), minSum));
                            }
                        }
                    } else {
                        predicates.add(criteriaBuilder
                                .equal(root.get(key), value));
                    }
                }
            }
        }

        return predicates;
    }

    @Override
    public void setSortedField(Pageable<Purchase> pageable, CriteriaBuilder
            criteriaBuilder, CriteriaQuery<Purchase> query, Root<Purchase> root) {
        String sortField = pageable.getSortField();
        if (StringUtils.isNotBlank(sortField)) {
            query.orderBy(criteriaBuilder.asc(root.get(sortField)));
        } else {
            query.orderBy(criteriaBuilder.asc(root.get(ATTR_ID)));
        }
    }

    @Override
    public Pageable<Purchase> getPageableRecords(Pageable<Purchase> pageable) {
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
            CriteriaQuery<Purchase> query = criteriaBuilder.createQuery(cls);
            Root<Purchase> root = query.from(cls);

            //filter
            List<Predicate> predicates = fillFilter(pageable, criteriaBuilder, root);
            Predicate[] predicatesArray = predicates.toArray(new Predicate[0]);
            if (predicatesArray.length > 0) {
                Predicate predicate =
                        criteriaBuilder.and(predicatesArray);
                query.where(predicate);
            }

            //order by
            setSortedField(pageable, criteriaBuilder, query, root);

            //pagination records
            int offset = (pageable.getPageNumber() - 1) *
                    pageable.getPageSize();
            TypedQuery<Purchase> resultQuery = entityManager.createQuery(query);
            resultQuery.setFirstResult(offset);
            resultQuery.setMaxResults(pageable.getPageSize());

            List<Purchase> resultList = resultQuery.getResultList();
            pageable.setRecords(resultList);

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
    public Long getCountRecords(Pageable<Purchase> pageable) {
        Long countRecords = null;
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder cbCount =
                    entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> queryCount =
                    cbCount.createQuery(LONG_CLASS);
            Root<Purchase> rootCount = queryCount.from(cls);

            //filter
            List<Predicate> predicates = fillFilter(pageable, cbCount, rootCount);

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
        } catch (
                Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        return countRecords;
    }

    private int getPages(Long countRecords, int pageSize) {
        long pages = countRecords / pageSize;
        pages = (countRecords % pageSize == 0) ? pages : pages + 1;
        return (int) (pages == 0 ? 1 : pages);
    }
}
