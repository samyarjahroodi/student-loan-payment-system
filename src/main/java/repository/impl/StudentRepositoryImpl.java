package repository.impl;

import base.reposiotry.Impl.BaseEntityRepositoryImpl;
import entity.student.Student;
import repository.StudentRepository;

import javax.persistence.EntityManager;

public class StudentRepositoryImpl
        extends BaseEntityRepositoryImpl<Student, Integer>
        implements StudentRepository {

    public StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Student> getEntityClass() {
        return Student.class;
    }


}
