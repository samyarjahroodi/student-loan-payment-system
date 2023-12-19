package service;

import base.service.BaseEntityService;
import entity.loan.LoanCategory;
import entity.student.Student;

import java.util.List;

public interface LoanCategoryService
        extends BaseEntityService<LoanCategory, Integer> {
    List<Long> getAmount(Student student);

}
