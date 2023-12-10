package service.impl;

import base.service.Impl.BaseEntityServiceImpl;
import entity.loan.LoanCategory;
import repository.impl.LoanCategoryRepositoryImpl;
import service.LoanCategoryService;

public class LoanCategoryServiceImpl
        extends BaseEntityServiceImpl<LoanCategory, Integer, LoanCategoryRepositoryImpl>
        implements LoanCategoryService {


    public LoanCategoryServiceImpl(LoanCategoryRepositoryImpl repository) {
        super(repository);
    }
}
