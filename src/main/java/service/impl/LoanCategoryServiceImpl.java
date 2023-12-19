package service.impl;

import base.service.Impl.BaseEntityServiceImpl;
import entity.loan.LoanCategory;
import entity.loan.TypeOfLoan;
import entity.student.Student;
import repository.impl.LoanCategoryRepositoryImpl;
import service.LoanCategoryService;


public class LoanCategoryServiceImpl
        extends BaseEntityServiceImpl<LoanCategory, Integer, LoanCategoryRepositoryImpl>
        implements LoanCategoryService {


    public LoanCategoryServiceImpl(LoanCategoryRepositoryImpl repository) {
        super(repository);
    }


    @Override
    public Long getAmount(Student student, TypeOfLoan typeOfLoan) {
        return repository.getAmount(student, typeOfLoan);
    }
}
