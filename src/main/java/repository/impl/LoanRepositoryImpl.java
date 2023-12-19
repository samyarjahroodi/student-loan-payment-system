package repository.impl;

import base.reposiotry.Impl.BaseEntityRepositoryImpl;
import entity.loan.Loan;
import entity.loan.TypeOfLoan;
import entity.student.Student;
import repository.LoanRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

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

    @Override
    public Loan loan(Student student, TypeOfLoan typeOfLoan) {
        try {
            return entityManager.createQuery("SELECT l FROM Loan l " +
                            "WHERE l.student = :student " +
                            "AND l.loanCategory.typeOfLoan = :loanType", Loan.class)
                    .setParameter("student", student)
                    .setParameter("loanType", typeOfLoan)
                    .getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return null;
    }

}
