package service;

import base.service.BaseEntityService;
import entity.loan.PaymentReport;

import java.util.List;

public interface PaymentReportService
        extends BaseEntityService<PaymentReport, Integer> {
    Double totalAmountOfPayments(List<PaymentReport> paymentReport);

}
