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
@Entity
@SuppressWarnings("unused")
public class LoanCategory extends BaseEntity<Integer> {
    @Column(name = "type_of_loan")
    private TypeOfLoan typeOfLoan;

    private Long amount;

    @Column(name = "payment_type")
    private PaymentType paymentType;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Loan> loan;
}
