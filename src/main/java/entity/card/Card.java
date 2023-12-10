package entity.card;

import base.entity.BaseEntity;
import entity.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SuppressWarnings("unused")
public class Card extends BaseEntity<Integer> {
    @Column(name = "card_number")
    private String cardNumber;

    private int cvv2;

    @Column(name = "expire_date")
    private Date expireDateOfCart;

    private Bank bank;

    @ManyToOne
    private Student student;

}
