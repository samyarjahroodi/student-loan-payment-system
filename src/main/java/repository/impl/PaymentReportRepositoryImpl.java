package repository.impl;

import base.reposiotry.Impl.BaseEntityRepositoryImpl;
import entity.loan.PaymentReport;
import entity.student.Student;
import repository.PaymentReportRepository;

import javax.persistence.EntityManager;
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

//    @Override
//    public Double totalAmountOfPayments(List<PaymentReport> paymentReport) {
//        return null;
//    }

    @Override
    public List<PaymentReport> unpaidInstallments(Student student) {
        return null;
    }
}
