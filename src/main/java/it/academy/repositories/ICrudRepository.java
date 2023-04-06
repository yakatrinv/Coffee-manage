package it.academy.repositories;

import it.academy.models.pageable.Pageable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public interface ICrudRepository<TEntity> {
    TEntity save(TEntity entity);

    TEntity update(TEntity entity);

    TEntity getById(Serializable id);

    void delete(Serializable id);

    List<TEntity> getAll();

    List<Predicate> fillFilter(Pageable<TEntity> pageable, CriteriaBuilder criteriaBuilder, Root<TEntity> root);

    void setSortedField(Pageable<TEntity> pageable, CriteriaBuilder criteriaBuilder, CriteriaQuery<TEntity> query, Root<TEntity> root);

    Pageable<TEntity> getPageableRecords(Pageable<TEntity> pageable);

    Long getCountRecords(Pageable<TEntity> pageable);
}
