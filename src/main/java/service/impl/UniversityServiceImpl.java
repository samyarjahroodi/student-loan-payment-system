package service.impl;

import base.service.Impl.BaseEntityServiceImpl;
import entity.university.University;
import repository.impl.UniversityRepositoryImpl;
import service.UniversityService;

public class UniversityServiceImpl
        extends BaseEntityServiceImpl<University, Integer, UniversityRepositoryImpl>
        implements UniversityService {

    public UniversityServiceImpl(UniversityRepositoryImpl repository) {
        super(repository);
    }
}
