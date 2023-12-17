package repository.impl;

import base.reposiotry.Impl.BaseEntityRepositoryImpl;
import entity.student.StudentSpouse;
import repository.StudentSpouseRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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

    @Override
    public StudentSpouse findStudentByNationalCode(String nationalCode) {
        try {
            return (StudentSpouse) entityManager.createQuery("SELECT s from StudentSpouse s where s.nationalCode = :nationalCode ")
                    .setParameter("nationalCode", nationalCode).getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean logIn(String username, String password) {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(s) FROM StudentSpouse s " +
                "WHERE s.username = :username AND s.password = :password", Long.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getSingleResult() > 0;
    }

}

