package it.academy.repositories.impl;

import it.academy.models.pageable.Pageable;
import it.academy.repositories.ICrudRepository;
import it.academy.utils.HibernateUtil;
import org.junit.platform.commons.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataGeneral.FLOAT_CLASS;
import static it.academy.utils.DataGeneral.LONG_CLASS;
import static it.academy.utils.DataGeneral.PERCENT_STRING;
import static it.academy.utils.DataGeneral.STRING_CLASS;
import static it.academy.utils.DataGeneral.STRING_FROM;

public class CrudRepository<TEntity> implements ICrudRepository<TEntity> {
    private EntityManager entityManager;

    private final Class<TEntity> cls;

    public CrudRepository(Class<TEntity> cls) {
        this.cls = cls;
    }

    @Override
    public TEntity save(TEntity entity) {
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public TEntity update(TEntity entity) {
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public void delete(Serializable id) {
        entityManager = HibernateUtil.getEntityManager();
        TEntity entity = entityManager.find(cls, id);
        if (entity != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(entity);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                entityManager.getTransaction().rollback();
            } finally {
                entityManager.close();
            }
        }
    }

    @Override
    public TEntity getById(Serializable id) {
        entityManager = HibernateUtil.getEntityManager();
        TEntity entity = entityManager.find(cls, id);
        entityManager.close();
        return entity;
    }

    @Override
    public List<TEntity> getAll() {
        entityManager = HibernateUtil.getEntityManager();
        String clsName = cls.getSimpleName();

        Query query = entityManager.createQuery(STRING_FROM + clsName);
        List<TEntity> resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    @Override
    public List<Predicate> fillFilter(Pageable<TEntity> pageable, CriteriaBuilder criteriaBuilder, Root<TEntity> root) {
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
    public void setSortedField(Pageable<TEntity> pageable, CriteriaBuilder criteriaBuilder, CriteriaQuery<TEntity> query, Root<TEntity> root) {
        String sortField = pageable.getSortField();
        if (StringUtils.isNotBlank(sortField)) {
            query.orderBy(criteriaBuilder.asc(root.get(sortField)));
        } else {
            query.orderBy(criteriaBuilder.asc(root.get(ATTR_ID)));
        }
    }

    @Override
    public Pageable<TEntity> getPageableRecords(Pageable<TEntity> pageable) {
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
            CriteriaQuery<TEntity> query = criteriaBuilder.createQuery(cls);
            Root<TEntity> root = query.from(cls);

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
            TypedQuery<TEntity> resultQuery = entityManager.createQuery(query);
            resultQuery.setFirstResult(offset);
            resultQuery.setMaxResults(pageable.getPageSize());

            List<TEntity> resultList = resultQuery.getResultList();
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
    public Long getCountRecords(Pageable<TEntity> pageable) {
        Long countRecords = null;
        try {
            entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();

            CriteriaBuilder cbCount =
                    entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> queryCount =
                    cbCount.createQuery(LONG_CLASS);
            Root<TEntity> rootCount = queryCount.from(cls);

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
