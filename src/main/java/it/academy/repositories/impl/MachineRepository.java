package it.academy.repositories.impl;

import it.academy.models.Machine;
import it.academy.models.Product;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IMachineRepository;
import it.academy.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_MACHINES;
import static it.academy.utils.Data.LONG_CLASS;
import static it.academy.utils.Data.MACHINE_CLASS;
import static it.academy.utils.Data.PERCENT_STRING;
import static it.academy.utils.Data.PRODUCT_CLASS;

public class MachineRepository extends CrudRepository<Machine>
        implements IMachineRepository {
    private EntityManager entityManager;

    public MachineRepository() {
        super(MACHINE_CLASS);
    }

    @Override
    public Pageable<Product> getProducts(Serializable id, Pageable<Product> pageable) {
        //count records
        Long countRecords = getProductsCount(id, pageable);
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

            CriteriaQuery<Product> query = criteriaBuilder.createQuery(PRODUCT_CLASS);
            Root<Product> root = query.from(PRODUCT_CLASS);

            Join<Product, Machine> machineJoin = root.join(ATTR_MACHINES);
            query.where(criteriaBuilder.equal(machineJoin.get(ATTR_ID), id));

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
                                    .like(root.get(key),
                                            PERCENT_STRING + value +
                                                    PERCENT_STRING));
                        }
                    } else if (value.getClass() == Float.class) {
                        if ((Float) value != 0) {
                            predicates.add(criteriaBuilder
                                    .ge(root.get(key), (Number) value));
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
            TypedQuery<Product> resultQuery = entityManager.createQuery(query);
            resultQuery.setFirstResult(offset);
            resultQuery.setMaxResults(pageable.getPageSize());

            List<Product> resultList = resultQuery.getResultList();
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
    public Long getProductsCount(Serializable id, Pageable<Product> pageable) {
        Long countRecords = null;
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            List<Predicate> predicates = new ArrayList<>();

            CriteriaBuilder cbCount =
                    entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> queryCount =
                    cbCount.createQuery(LONG_CLASS);

            Root<Product> rootCount = queryCount.from(PRODUCT_CLASS);
            Join<Product, Machine> machineJoin = rootCount.join(ATTR_MACHINES);
            queryCount.where(cbCount.equal(machineJoin.get(ATTR_ID), id));

            //filtered
            HashMap<String, Object> filteredFields
                    = pageable.getSearchFields();
            for (Map.Entry<String, Object> pair : filteredFields.entrySet()) {
                String key = pair.getKey();
                Object value = pair.getValue();

                if (isNotEmpty(key) && value != null) {
                    if (value.getClass() == String.class) {
                        if (!((String) value).isEmpty()) {
                            predicates
                                    .add(cbCount
                                            .like(rootCount.get(key),
                                                    PERCENT_STRING + value +
                                                            PERCENT_STRING));
                        }
                    } else if (value.getClass() == Float.class) {
                        if ((Float) value != 0) {
                            predicates.add(cbCount
                                    .ge(rootCount.get(key), (Number) value));
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
        return (int) ((countRecords % pageSize == 0) ? pages : pages + 1);
    }

    private boolean isNotEmpty(String value) {
        return (value != null && !value.isEmpty());
    }

    @Override
    public void addProductInMachine(Integer machineId, Integer productId) {
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            Machine machine = entityManager.find(MACHINE_CLASS, machineId);
            Product product = entityManager.find(PRODUCT_CLASS, productId);

            Set<Product> products = machine.getProducts();
            if (!products.contains(product)) {
                products.add(product);
                machine.setProducts(products);

                entityManager.merge(machine);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteProductInMachine(Integer machineId, Integer productId) {
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            Machine machine = entityManager.find(MACHINE_CLASS, machineId);
            Product product = entityManager.find(PRODUCT_CLASS, productId);

            Set<Product> products = machine.getProducts();
            if (products.contains(product)) {
                products.remove(product);
                machine.setProducts(products);

                entityManager.merge(machine);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
