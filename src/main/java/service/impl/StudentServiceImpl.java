package service.impl;

import base.service.Impl.BaseEntityServiceImpl;
import entity.student.Student;
import repository.impl.StudentRepositoryImpl;
import service.StudentService;

public class StudentServiceImpl
        extends BaseEntityServiceImpl<Student, Integer, StudentRepositoryImpl>
        implements StudentService {


    public StudentServiceImpl(StudentRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public boolean logIn(String username, String password) {
        return repository.logIn(username, password);
    }

    @Override
    public Student findStudentByNationalCode(String nationalCode) {
        return repository.findStudentByNationalCode(nationalCode);
    }
}
