package entity.card;

import base.entity.BaseEntity;
import entity.student.Student;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@SuppressWarnings("unused")
public class Card extends BaseEntity<Integer> {
    @Column(name = "card_number", unique = true)
    private String cardNumber;

    private int cvv2;

    @Column(name = "expire_date")
    private LocalDate expireDateOfCart;

    @Enumerated(EnumType.STRING)
    private Bank bank;

    @ManyToOne
    private Student student;

    @Column(name = "amount_of_credit")
    private Long amountOfAccount;

    @Override
    public String toString() {
        return "Card{" +
                "\ncardNumber='" + cardNumber + '\'' +
                ", cvv2=" + cvv2 +
                ", expireDateOfCart=" + expireDateOfCart + '\n' +
                ", bank=" + bank + '\n' +
                ", amountOfAccount=" + amountOfAccount +
                '}';
    }
}
