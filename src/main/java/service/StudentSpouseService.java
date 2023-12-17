package service;

import base.service.BaseEntityService;
import entity.student.StudentSpouse;

public interface StudentSpouseService
        extends BaseEntityService<StudentSpouse, Integer> {
    StudentSpouse findStudentByNationalCode(String nationalCode);

    boolean logIn(String nationalCode, String password);

}
