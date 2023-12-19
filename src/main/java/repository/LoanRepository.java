package repository;

import base.reposiotry.BaseEntityRepository;
import entity.loan.Loan;
import entity.loan.TypeOfLoan;
import entity.student.Student;

public interface LoanRepository
        extends BaseEntityRepository<Loan, Integer> {
    Loan loan(Student student, TypeOfLoan typeOfLoan);
}
