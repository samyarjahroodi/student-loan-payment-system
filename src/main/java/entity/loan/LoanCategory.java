package entity.loan;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@SuppressWarnings("unused")
public class LoanCategory extends BaseEntity<Integer> {
    @Column(name = "type_of_loan")
    @Enumerated(EnumType.STRING)
    private TypeOfLoan typeOfLoan;

    private Long amount;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loanCategory")
    private List<Loan> loan;

    @Column(name = "housing_rental_agreement_number")
    private String housingRentalAgreementNumber;

}
