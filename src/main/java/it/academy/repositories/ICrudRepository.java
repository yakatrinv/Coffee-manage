package it.academy.repositories;

import it.academy.models.pageable.Pageable;

import java.io.Serializable;
import java.util.List;

public interface ICrudRepository<TEntity> {
    TEntity save(TEntity entity);

    TEntity update(TEntity entity);

    TEntity getById(Serializable id);

    void delete(Serializable id);

    List<TEntity> getAll();

    Pageable<TEntity> getPageableRecords(Pageable<TEntity> pageable);

    Long getCountRecords(Pageable<TEntity> pageable);
}
