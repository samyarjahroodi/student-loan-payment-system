package service.impl;

import base.service.Impl.BaseEntityServiceImpl;
import entity.loan.PaymentReport;
import entity.loan.TypeOfLoan;
import entity.student.Student;
import repository.impl.PaymentReportRepositoryImpl;
import service.PaymentReportService;

import java.time.LocalDate;
import java.util.List;

public class PaymentReportServiceImpl
        extends BaseEntityServiceImpl<PaymentReport, Integer, PaymentReportRepositoryImpl>
        implements PaymentReportService {

    @Override
    public void payPaymentReport(Student student, Integer id, TypeOfLoan typeOfLoan, LocalDate paymentDate) {
        repository.payPaymentReport(student, id, typeOfLoan, paymentDate);
    }

    @Override
    public PaymentReport getPaymentReportBasedOnLoanNumber(Student student, Integer id) {
        return repository.getPaymentReportBasedOnLoanNumber(student, id);
    }

    @Override
    public List<PaymentReport> unpaidInstallmentsBasedOnTypeOfLoan(Student student, TypeOfLoan typeOfLoan) {
        return repository.unpaidInstallmentsBasedOnTypeOfLoan(student, typeOfLoan);
    }

    public PaymentReportServiceImpl(PaymentReportRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public List<PaymentReport> unpaidInstallments(Student student) {
        return repository.unpaidInstallments(student);
    }

    @Override
    public List<PaymentReport> paidInstallments(Student student) {
        return repository.paidInstallments(student);
    }



}
