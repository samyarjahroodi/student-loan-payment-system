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

    @Override
    public StudentSpouse findStudentByNationalCode(String nationalCode) {
        return repository.findStudentByNationalCode(nationalCode);
    }

    @Override
    public boolean logIn(String nationalCode, String password) {
        return repository.logIn(nationalCode, password);
    }
}
