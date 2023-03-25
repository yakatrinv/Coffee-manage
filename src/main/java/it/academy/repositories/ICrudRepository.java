package it.academy.repositories;

import java.io.Serializable;
import java.util.List;

public interface ICrudRepository<TEntity> {
    TEntity save(TEntity entity);

    TEntity update(TEntity entity);

    TEntity getById(Serializable id);

    void delete(Serializable id);

    List<TEntity> getAll();
}
