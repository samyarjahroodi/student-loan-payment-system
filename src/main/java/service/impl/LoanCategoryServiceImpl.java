package service.impl;

import base.service.Impl.BaseEntityServiceImpl;
import entity.loan.LoanCategory;
import entity.student.Student;
import repository.impl.LoanCategoryRepositoryImpl;
import service.LoanCategoryService;

import java.util.List;

public class LoanCategoryServiceImpl
        extends BaseEntityServiceImpl<LoanCategory, Integer, LoanCategoryRepositoryImpl>
        implements LoanCategoryService {


    public LoanCategoryServiceImpl(LoanCategoryRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public List<Long> getAmount(Student student) {
        return repository.getAmount(student);
    }
}
