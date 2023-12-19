package repository;

import base.reposiotry.BaseEntityRepository;
import entity.loan.LoanCategory;
import entity.loan.TypeOfLoan;
import entity.student.Student;

import java.util.List;

public interface LoanCategoryRepository
        extends BaseEntityRepository<LoanCategory, Integer> {
    Long getAmount(Student student, TypeOfLoan typeOfLoan);
}
