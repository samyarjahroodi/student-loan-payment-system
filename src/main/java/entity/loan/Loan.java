package entity.loan;

import base.entity.BaseEntity;
import entity.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuppressWarnings("unused")
@Entity
public class Loan extends BaseEntity<Integer> {
    @ManyToOne
    private LoanCategory loanCategory;

    @ManyToOne
    private Student student;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    @Column(name = "payment_report")
    private List<PaymentReport> paymentReport;

    private String housingRentalAgreementNumber;
}
