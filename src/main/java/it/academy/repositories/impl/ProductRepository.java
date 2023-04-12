package it.academy.repositories.impl;

import it.academy.models.Machine;
import it.academy.models.Product;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IProductRepository;
import it.academy.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataGeneral.LONG_CLASS;
import static it.academy.utils.DataGeneral.PRODUCT_CLASS;
import static it.academy.utils.DataMachine.ATTR_MACHINES;

public class ProductRepository extends CrudRepository<Product>
        implements IProductRepository {
    private EntityManager entityManager;

    public ProductRepository() {
        super(PRODUCT_CLASS);
    }

    @Override
    public Pageable<Product> getProductsMachine(Serializable id, Pageable<Product> pageable) {
        //count records
        Long countRecords = getProductsMachineCount(id, pageable);
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

            //filter
            List<Predicate> predicates = fillFilter(pageable, criteriaBuilder, root);
            Predicate[] predicatesArray = predicates.toArray(new Predicate[0]);
            if (predicatesArray.length > 0) {
                Predicate predicate =
                        criteriaBuilder.and(predicatesArray);
                query.where(predicate);
            }

            setSortedField(pageable, criteriaBuilder, query, root);

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
    public Long getProductsMachineCount(Serializable id, Pageable<Product> pageable) {
        Long countRecords = null;
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder cbCount =
                    entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> queryCount =
                    cbCount.createQuery(LONG_CLASS);

            Root<Product> rootCount = queryCount.from(PRODUCT_CLASS);
            Join<Product, Machine> machineJoin = rootCount.join(ATTR_MACHINES);
            queryCount.where(cbCount.equal(machineJoin.get(ATTR_ID), id));

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
