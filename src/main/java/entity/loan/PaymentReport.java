package entity.loan;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@SuppressWarnings("unused")
public class PaymentReport extends BaseEntity<Integer> {

    @ManyToOne
    private Loan loan;

    @Column(name = "loan_number")
    private int loanNumber;

    private Long amountPerPayment;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "is_paid", columnDefinition = "boolean default false")
    private boolean isPaid;

}
