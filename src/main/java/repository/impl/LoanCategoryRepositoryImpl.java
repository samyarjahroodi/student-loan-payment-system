package repository.impl;

import base.reposiotry.Impl.BaseEntityRepositoryImpl;
import entity.loan.LoanCategory;
import entity.loan.TypeOfLoan;
import entity.student.Student;
import repository.LoanCategoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

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

    @Override
    public Long getAmount(Student student, TypeOfLoan typeOfLoan) {
        try {
            return entityManager.createQuery("SELECT SUM(l.loanCategory.amount) FROM Student s " +
                            "JOIN s.loans l " +
                            "JOIN l.loanCategory lc " +
                            "WHERE s.id = :studentId " +
                            "AND lc.typeOfLoan = :loanType", Long.class)
                    .setParameter("studentId", student.getId())
                    .setParameter("loanType", typeOfLoan)
                    .getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return null;
    }

}
