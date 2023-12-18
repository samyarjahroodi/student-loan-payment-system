package entity.loan;

import base.entity.BaseEntity;
import entity.student.Student;
import entity.student.StudentSpouse;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Loan extends BaseEntity<Integer> {
    @ManyToOne
    @JoinColumn(name = "loan_category")
    private LoanCategory loanCategory;

    @ManyToOne
    private Student student;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    @Column(name = "payment_report")
    private List<PaymentReport> paymentReport;

    @ManyToOne(cascade = CascadeType.ALL)
    private StudentSpouse studentSpouse;

    @Column(name = "date_that_loan_has_been_paid")
    private LocalDate dateThatLoanHasBeenGet;
}
