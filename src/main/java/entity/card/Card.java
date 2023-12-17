package entity.card;

import base.entity.BaseEntity;
import entity.student.Student;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@SuppressWarnings("unused")
public class Card extends BaseEntity<Integer> {
    @Column(name = "card_number")
    private String cardNumber;

    private int cvv2;

    @Column(name = "expire_date")
    @Temporal(TemporalType.DATE)
    private Date expireDateOfCart;

    @Enumerated(EnumType.STRING)
    private Bank bank;

    @ManyToOne
    private Student student;

    private BigDecimal amountOfAccount;

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cvv2=" + cvv2 +
                ", expireDateOfCart=" + expireDateOfCart +
                ", bank=" + bank +
                ", amountOfAccount=" + amountOfAccount +
                '}';
    }
}
