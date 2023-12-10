package entity.loan;

import base.entity.BaseEntity;
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
    @OneToOne
    //@Column(name = "loan_category")
    private LoanCategory loanCategory;


    @OneToMany(mappedBy = "loan")
    @Column(name = "payment_report")
    private List<PaymentReport> paymentReport;


}
