package entity.loan;

import base.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;


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

    private Double amountPerPayment;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "is_paid", columnDefinition = "boolean default false")
    private boolean isPaid;

    @Override
    public String toString() {
        return "PaymentReport{" +
                "\nloan=" + (loan != null ? loan.toString() : "null") +
                "\nloanNumber=" + loanNumber +
                "\namountPerPayment=" + amountPerPayment +
                "\ndueDate=" + dueDate +
                "\npaymentDate=" + paymentDate +
                "\nisPaid=" + isPaid +
                "\n}";
    }

}
