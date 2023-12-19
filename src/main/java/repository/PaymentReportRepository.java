package repository;

import base.reposiotry.BaseEntityRepository;
import entity.loan.PaymentReport;
import entity.student.Student;

import java.util.List;

public interface PaymentReportRepository
        extends BaseEntityRepository<PaymentReport, Integer> {

    List<PaymentReport> unpaidInstallments(Student student);

//    Double totalAmountOfPayments(List<PaymentReport> paymentReport);
}
