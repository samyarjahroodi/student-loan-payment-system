package repository.impl;

import base.reposiotry.Impl.BaseEntityRepositoryImpl;
import entity.loan.PaymentReport;
import entity.loan.TypeOfLoan;
import entity.student.Student;
import repository.PaymentReportRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class PaymentReportRepositoryImpl
        extends BaseEntityRepositoryImpl<PaymentReport, Integer>
        implements PaymentReportRepository {

    public PaymentReportRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<PaymentReport> getEntityClass() {
        return PaymentReport.class;
    }


    @Override
    public List<PaymentReport> unpaidInstallments(Student student) {
        return entityManager.createQuery("SELECT pr FROM PaymentReport pr " +
                        "WHERE pr.loan.student = :student " +
                        "AND pr.isPaid = false", PaymentReport.class)
                .setParameter("student", student)
                .getResultList();
    }

    @Override
    public List<PaymentReport> paidInstallments(Student student) {
        return entityManager.createQuery("SELECT pr FROM PaymentReport pr " +
                        "WHERE pr.loan.student = :student " +
                        "AND pr.isPaid = true", PaymentReport.class)
                .setParameter("student", student)
                .getResultList();
    }

    @Override
    public PaymentReport getPaymentReportBasedOnLoanNumber(Student student, Integer id) {
        try {
            String jpql = "SELECT pr FROM PaymentReport pr " +
                    "WHERE pr.loan.student = :student " +
                    "AND pr.loanNumber = :loanNumber";

            TypedQuery<PaymentReport> query = entityManager.createQuery(jpql, PaymentReport.class);
            query.setParameter("student", student);
            query.setParameter("loanNumber", id);
            query.setMaxResults(1);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void payPaymentReport(Student student, Integer loanNumber, TypeOfLoan typeOfLoan, LocalDate paymentDate) {

        try {
            PaymentReport paymentReport = entityManager.createQuery(
                            "SELECT pr FROM PaymentReport pr " +
                                    "WHERE pr.loan.student = :student " +
                                    "AND pr.loanNumber = :loanNumber " +
                                    "AND pr.loan.loanCategory.typeOfLoan = :typeOfLoan", PaymentReport.class)
                    .setParameter("student", student)
                    .setParameter("loanNumber", loanNumber)
                    .setParameter("typeOfLoan", typeOfLoan)
                    .getSingleResult();


            paymentReport.setPaid(true);
            paymentReport.setPaymentDate(paymentDate);
            saveOrUpdate(paymentReport);
        } catch (NoResultException | NonUniqueResultException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<PaymentReport> unpaidInstallmentsBasedOnTypeOfLoan(Student student, TypeOfLoan typeOfLoan) {
        try {
            return entityManager.createQuery(
                            "SELECT pr FROM PaymentReport pr " +
                                    "WHERE pr.loan.student = :student " +
                                    "AND pr.isPaid = false " +
                                    "AND pr.loan.loanCategory.typeOfLoan = :typeOfLoan", PaymentReport.class)
                    .setParameter("student", student)
                    .setParameter("typeOfLoan", typeOfLoan)
                    .getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
