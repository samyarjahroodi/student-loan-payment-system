package repository;

import base.reposiotry.BaseEntityRepository;
import entity.student.Student;
import entity.student.StudentSpouse;

public interface StudentSpouseRepository
        extends BaseEntityRepository<StudentSpouse, Integer> {

    StudentSpouse findStudentByNationalCode(String nationalCode);

    boolean logIn(String nationalCode, String password);
}
