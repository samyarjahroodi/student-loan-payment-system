package repository;

import base.reposiotry.BaseEntityRepository;
import entity.loan.LoanCategory;
import entity.student.Student;

import java.util.List;

public interface LoanCategoryRepository
        extends BaseEntityRepository<LoanCategory, Integer> {
    List<Long> getAmount(Student student);
}
