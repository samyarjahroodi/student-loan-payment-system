package repository.impl;

import base.reposiotry.Impl.BaseEntityRepositoryImpl;
import entity.student.Student;
import entity.student.StudentSpouse;
import repository.StudentSpouseRepository;

import javax.persistence.EntityManager;

public class StudentSpouseRepositoryImpl
        extends BaseEntityRepositoryImpl<StudentSpouse, Integer>
        implements StudentSpouseRepository {

    public StudentSpouseRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<StudentSpouse> getEntityClass() {
        return StudentSpouse.class;
    }
}
