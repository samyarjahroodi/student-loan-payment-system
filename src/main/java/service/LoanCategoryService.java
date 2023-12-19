package service;

import base.service.BaseEntityService;
import entity.loan.LoanCategory;
import entity.loan.TypeOfLoan;
import entity.student.Student;

import java.util.List;

public interface LoanCategoryService
        extends BaseEntityService<LoanCategory, Integer> {
    Long getAmount(Student student, TypeOfLoan typeOfLoan);


}
