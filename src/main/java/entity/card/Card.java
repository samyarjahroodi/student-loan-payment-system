package entity.card;

import base.entity.BaseEntity;
import entity.student.Student;
import lombok.*;

import javax.persistence.*;
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

    public Card(Integer integer, String cardNumber, int cvv2, Date expireDateOfCart, Bank bank, Student student) {
        super(integer);
        this.cardNumber = cardNumber;
        this.cvv2 = cvv2;
        this.expireDateOfCart = expireDateOfCart;
        this.bank = bank;
        this.student = student;
    }
}
