package it.academy.repositories.impl;

import it.academy.models.Machine;
import it.academy.models.Product;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IMachineRepository;
import it.academy.utils.HibernateUtil;
import org.junit.platform.commons.util.StringUtils;

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
import java.util.Set;

import static it.academy.utils.DataAddress.ATTR_ADDRESS;
import static it.academy.utils.DataAddress.ATTR_CITIES;
import static it.academy.utils.DataAddress.ATTR_CITY;
import static it.academy.utils.DataAddress.ATTR_STREET;
import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataGeneral.FLOAT_CLASS;
import static it.academy.utils.DataGeneral.LONG_CLASS;
import static it.academy.utils.DataGeneral.MACHINE_CLASS;
import static it.academy.utils.DataGeneral.PERCENT_STRING;
import static it.academy.utils.DataGeneral.PRODUCT_CLASS;
import static it.academy.utils.DataGeneral.STRING_ARRAY_CLASS;
import static it.academy.utils.DataGeneral.STRING_CLASS;
import static it.academy.utils.DataModel.ATTR_BRAND;
import static it.academy.utils.DataModel.ATTR_MODEL;
import static it.academy.utils.DataModel.ATTR_NAME_MODEL;

public class MachineRepository extends CrudRepository<Machine>
        implements IMachineRepository {
    private EntityManager entityManager;
    private final Class<Machine> cls = MACHINE_CLASS;

    public MachineRepository() {
        super(MACHINE_CLASS);
    }

    @Override
    public void addProductInMachine(Integer machineId, Integer productId) {
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            Machine machine = entityManager.find(cls, machineId);
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

            Machine machine = entityManager.find(cls, machineId);
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

    @Override
    public List<Predicate> fillFilter(Pageable<Machine> pageable, CriteriaBuilder criteriaBuilder, Root<Machine> root) {
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
                            if (ATTR_BRAND.equalsIgnoreCase(key)
                                    || ATTR_NAME_MODEL.equals(key)) {
                                root.join(ATTR_MODEL);
                                predicates.add(criteriaBuilder
                                        .like(root.get(ATTR_MODEL).get(key),
                                                PERCENT_STRING + value +
                                                        PERCENT_STRING));
                            } else if (ATTR_CITY.equalsIgnoreCase(key)
                                    || ATTR_STREET.equals(key)) {
                                root.join(ATTR_ADDRESS);
                                predicates.add(criteriaBuilder
                                        .like(root.get(ATTR_ADDRESS).get(key),
                                                PERCENT_STRING + value +
                                                        PERCENT_STRING));
                            } else {
                                predicates.add(criteriaBuilder
                                        .like(root.get(key),
                                                PERCENT_STRING + value +
                                                        PERCENT_STRING));
                            }
                        }
                    } else if (value.getClass() == STRING_ARRAY_CLASS) {
                        if (ATTR_CITIES.equalsIgnoreCase(key)) {
                            root.join(ATTR_ADDRESS);
                            if (value instanceof Object[]) {
                                predicates.add(criteriaBuilder.and(root.get(ATTR_ADDRESS).get(ATTR_CITY).in((Object[]) value)));
                            }
                        }
                    } else if (value.getClass() == FLOAT_CLASS) {
                        if (value instanceof Float && (Float) value != 0) {
                            predicates.add(criteriaBuilder
                                    .equal(root.get(key), value));
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
    public void setSortedField(Pageable<Machine> pageable,
                               CriteriaBuilder criteriaBuilder,
                               CriteriaQuery<Machine> query,
                               Root<Machine> root) {
        String sortField = pageable.getSortField();
        if (StringUtils.isNotBlank(sortField)) {
            query.orderBy(criteriaBuilder.asc(root.get(sortField)));
        } else {
            query.orderBy(criteriaBuilder.asc(root.get(ATTR_ID)));
        }
    }

    @Override
    public Pageable<Machine> getPageableRecords(Pageable<Machine> pageable) {
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
            CriteriaQuery<Machine> query = criteriaBuilder.createQuery(cls);
            Root<Machine> root = query.from(cls);

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
            TypedQuery<Machine> resultQuery = entityManager.createQuery(query);
            resultQuery.setFirstResult(offset);
            resultQuery.setMaxResults(pageable.getPageSize());

            List<Machine> resultList = resultQuery.getResultList();
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
    public Long getCountRecords(Pageable<Machine> pageable) {
        Long countRecords = null;
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder cbCount =
                    entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> queryCount =
                    cbCount.createQuery(LONG_CLASS);
            Root<Machine> rootCount = queryCount.from(cls);

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
        if (countRecords == 0) {
            return (int) (pages + 1);
        } else {
            return (int) ((countRecords % pageSize == 0) ? pages : pages + 1);
        }
    }
}
