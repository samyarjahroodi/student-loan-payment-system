package repository.impl;

import base.reposiotry.Impl.BaseEntityRepositoryImpl;
import entity.loan.Loan;
import repository.LoanRepository;

import javax.persistence.EntityManager;

public class LoanRepositoryImpl
extends BaseEntityRepositoryImpl<Loan,Integer>
implements LoanRepository {
    public LoanRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Loan> getEntityClass() {
        return Loan.class;
    }
}
