package repository.impl;

import base.reposiotry.Impl.BaseEntityRepositoryImpl;
import entity.loan.LoanCategory;
import repository.LoanCategoryRepository;

import javax.persistence.EntityManager;

public class LoanCategoryRepositoryImpl
        extends BaseEntityRepositoryImpl<LoanCategory, Integer>
        implements LoanCategoryRepository {

    public LoanCategoryRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<LoanCategory> getEntityClass() {
        return LoanCategory.class;
    }
}
