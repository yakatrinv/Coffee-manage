package it.academy.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface ICrudRepository<TEntity> {
    Optional<TEntity> save(TEntity entity);

    Optional<TEntity> update(TEntity entity);

    Optional<TEntity> getById(Serializable id);

    void delete(Serializable id);

    List<TEntity> getAll();
}
