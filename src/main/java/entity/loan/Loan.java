package entity.loan;

import base.entity.BaseEntity;
import entity.student.Student;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuppressWarnings("unused")
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

}
