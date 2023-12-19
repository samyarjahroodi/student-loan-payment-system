package service.impl;

import base.service.Impl.BaseEntityServiceImpl;
import entity.loan.Loan;
import entity.loan.TypeOfLoan;
import entity.student.Student;
import repository.impl.LoanRepositoryImpl;
import service.LoanService;

public class LoanServiceImpl
        extends BaseEntityServiceImpl<Loan, Integer, LoanRepositoryImpl>
        implements LoanService {

    public LoanServiceImpl(LoanRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public Loan loan(Student student, TypeOfLoan typeOfLoan) {
        return repository.loan(student, typeOfLoan);
    }
}
