package service.impl;

import base.service.Impl.BaseEntityServiceImpl;
import entity.student.StudentSpouse;
import repository.impl.StudentSpouseRepositoryImpl;
import service.StudentSpouseService;

public class StudentSpouseServiceImpl
        extends BaseEntityServiceImpl<StudentSpouse, Integer, StudentSpouseRepositoryImpl>
        implements StudentSpouseService {

    public StudentSpouseServiceImpl(StudentSpouseRepositoryImpl repository) {
        super(repository);
    }
}
