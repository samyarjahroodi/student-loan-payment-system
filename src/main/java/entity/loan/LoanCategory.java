package entity.loan;

import base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SuppressWarnings("unused")
public class LoanCategory extends BaseEntity<Integer> {
    @Column(name = "type_of_loan")
    private TypeOfLoan typeOfLoan;

    private Long amount;

    @Column(name = "payment_type")
    private PaymentType paymentType;

    @OneToOne
    private Loan loan;
}
