package repository.impl;

import base.reposiotry.Impl.BaseEntityRepositoryImpl;
import entity.student.Student;
import repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
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
    public boolean logIn(String username, String password) {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(s) FROM Student s " +
                "WHERE s.username = :username AND s.password = :password", Long.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getSingleResult() > 0;
    }

    @Override
    public Student findStudentByNationalCode(String nationalCode) {
        try {
            return (Student) entityManager.createQuery("SELECT s from Student s where s.nationalCode = :nationalCode ")
                    .setParameter("nationalCode", nationalCode).getSingleResult();

        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return null;
    }
}
