package service.impl;

import base.service.Impl.BaseEntityServiceImpl;
import entity.loan.PaymentReport;
import repository.impl.PaymentReportRepositoryImpl;
import service.PaymentReportService;

import java.util.List;

public class PaymentReportServiceImpl
        extends BaseEntityServiceImpl<PaymentReport, Integer, PaymentReportRepositoryImpl>
        implements PaymentReportService {

    public PaymentReportServiceImpl(PaymentReportRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public Double totalAmountOfPayments(List<PaymentReport> paymentReports) {
        if (paymentReports == null || paymentReports.isEmpty()) {
            return 0.0;
        }
        double totalAmount = 0.0;
        for (PaymentReport paymentReport : paymentReports) {
            if (paymentReport != null && paymentReport.getAmountPerPayment() != null) {
                totalAmount += paymentReport.getAmountPerPayment();
            }
        }
        return totalAmount;
    }

}
