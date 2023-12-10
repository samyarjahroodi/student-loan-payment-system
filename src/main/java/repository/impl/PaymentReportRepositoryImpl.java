package repository.impl;

import base.reposiotry.Impl.BaseEntityRepositoryImpl;
import entity.loan.PaymentReport;
import service.PaymentReportService;

import javax.persistence.EntityManager;

public class PaymentReportRepositoryImpl
        extends BaseEntityRepositoryImpl<PaymentReport, Integer>
        implements PaymentReportService {

    public PaymentReportRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<PaymentReport> getEntityClass() {
        return PaymentReport.class;
    }
}
