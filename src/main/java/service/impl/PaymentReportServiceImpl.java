package service.impl;

import base.service.Impl.BaseEntityServiceImpl;
import entity.loan.PaymentReport;
import repository.impl.PaymentReportRepositoryImpl;
import service.PaymentReportService;

public class PaymentReportServiceImpl
        extends BaseEntityServiceImpl<PaymentReport, Integer, PaymentReportRepositoryImpl>
        implements PaymentReportService {

    public PaymentReportServiceImpl(PaymentReportRepositoryImpl repository) {
        super(repository);
    }
}
