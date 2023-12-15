package repository;

import base.reposiotry.BaseEntityRepository;
import entity.student.Student;

public interface StudentRepository
        extends BaseEntityRepository<Student, Integer> {

    boolean logIn(String nationalCode, String password);
}
