package it.academy.repositories.impl;

import it.academy.repositories.ICrudRepository;
import it.academy.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

import static it.academy.utils.Data.STRING_FROM;

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
}
