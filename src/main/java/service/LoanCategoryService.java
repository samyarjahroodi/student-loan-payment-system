package service;

import base.service.BaseEntityService;
import entity.loan.LoanCategory;
import entity.loan.TypeOfLoan;
import entity.student.Student;


public interface LoanCategoryService
        extends BaseEntityService<LoanCategory, Integer> {
    Long getAmount(Student student, TypeOfLoan typeOfLoan);


}
