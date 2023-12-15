package repository.impl;

import base.reposiotry.Impl.BaseEntityRepositoryImpl;
import entity.student.Student;
import repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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


    @Override
    public boolean logIn(String nationalCode, String password) {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(s) FROM Student s " +
                "WHERE s.nationalCode = :nationalCode AND s.password = :password", Long.class);
        query.setParameter("nationalCode", nationalCode);
        query.setParameter("password", password);
        return query.getSingleResult() > 0;
    }
}
