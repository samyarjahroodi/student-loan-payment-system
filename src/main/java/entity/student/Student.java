package entity.student;

import entity.Person;
import entity.card.Card;
import entity.university.University;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class Student extends Person {
    @ManyToOne
    private University university;

    @Column(nullable = false, unique = true, name = "student_code")
    private String studentCode;

    @Column(nullable = false, name = "entrance_year")
    private Date entranceYear;

    @Column(nullable = false)
    private Grade grade;

    @OneToMany(mappedBy = "student")
    private Set<Card> cards;

}
