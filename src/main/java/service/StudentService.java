package service;

import base.service.BaseEntityService;
import entity.student.Student;

public interface StudentService
        extends BaseEntityService<Student, Integer> {
    boolean logIn(String nationalCode, String password);

    Student findStudentByNationalCode(String nationalCode);

}
