package base.service.Impl;

import base.entity.BaseEntity;
import base.reposiotry.BaseEntityRepository;
import base.service.BaseEntityService;


import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

@SuppressWarnings("unused")
public class BaseEntityServiceImpl
        <T extends BaseEntity<ID>, ID extends Serializable, R extends BaseEntityRepository<T, ID>>
        implements BaseEntityService<T, ID> {
    protected final R repository;

    public BaseEntityServiceImpl(R repository) {
        this.repository = repository;
    }


    @Override
    public void saveOrUpdate(T entity) {
        repository.saveOrUpdate(entity);
    }

    @Override
    public void delete(ID id) {
        repository.delete(id);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public Collection<T> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Override
    public void beginTransaction() {
        repository.beginTransaction();
    }

    @Override
    public void commitTransaction() {
        repository.commitTransaction();
    }

    @Override
    public void rollBack() {
        repository.rollBack();
    }
}
