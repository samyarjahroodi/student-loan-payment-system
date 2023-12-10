package repository.impl;

import base.reposiotry.BaseEntityRepository;
import base.reposiotry.Impl.BaseEntityRepositoryImpl;
import entity.university.University;
import repository.UniversityRepository;

import javax.persistence.EntityManager;

public class UniversityRepositoryImpl
        extends BaseEntityRepositoryImpl<University, Integer>
        implements UniversityRepository {
    public UniversityRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<University> getEntityClass() {
        return University.class;
    }
}
