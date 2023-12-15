package entity.loan;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
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
    private TypeOfLoan typeOfLoan;

    private Long amount;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Loan> loan;

    private String city;
    @Column(name = "housing_rental_agreement_number")
    private String housingRentalAgreementNumber;

}
