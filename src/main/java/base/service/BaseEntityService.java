package base.service;

import base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface BaseEntityService<T extends BaseEntity<ID>, ID extends Serializable> {
    void saveOrUpdate(T entity);

    void delete(ID id);

    Optional<T> findById(ID id);

    Collection<T> findAll();

    boolean existsById(ID id);

    void beginTransaction();

    void commitTransaction();

    void rollBack();
}
