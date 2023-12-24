package service;

import base.service.BaseEntityService;
import entity.loan.PaymentReport;
import entity.loan.TypeOfLoan;
import entity.student.Student;

import java.time.LocalDate;
import java.util.List;

public interface PaymentReportService
        extends BaseEntityService<PaymentReport, Integer> {

    List<PaymentReport> unpaidInstallments(Student student);

    List<PaymentReport> paidInstallments(Student student);

    void payPaymentReport(Student student, Integer id, TypeOfLoan typeOfLoan, LocalDate paymentDate);

    List<PaymentReport> unpaidInstallmentsBasedOnTypeOfLoan(Student student, TypeOfLoan typeOfLoan);

    PaymentReport getPaymentReportBasedOnLoanNumber(Student student, Integer id);

}
