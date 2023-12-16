package base.reposiotry;

import base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

@SuppressWarnings("unused")
public interface BaseEntityRepository
        <T , ID extends Serializable> {

    T saveOrUpdate(T entity);

    void delete(ID id);

    Optional<T> findById(ID id);

    Collection<T> findAll();

    boolean existsById(ID id);

    void beginTransaction();

    void commitTransaction();

    void rollBack();
}
