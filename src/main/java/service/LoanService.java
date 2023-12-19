package service;

import base.service.BaseEntityService;
import entity.loan.Loan;
import entity.loan.TypeOfLoan;
import entity.student.Student;

public interface LoanService
        extends BaseEntityService<Loan, Integer> {
    Loan loan(Student student, TypeOfLoan typeOfLoan);
}
