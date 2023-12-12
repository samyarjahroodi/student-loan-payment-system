package entity.student;

import entity.Person;
import entity.card.Card;
import entity.loan.Loan;
import entity.university.TypeOfUniversity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class Student extends Person {
    @Column(unique = true, name = "student_code")
    private String studentCode;

    @Column(nullable = false, name = "entrance_year")
    private Integer entranceYear;

    @Column(nullable = false)
    private Grade grade;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Card> cards;

    private String password;

    private boolean isMarried;

    private boolean accomodateInUniversity;

    @Column(name = "name")
    private String nameOfUniversity;

    private TypeOfUniversity typeOfUniversity;


    private String address;

    private String city;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Loan> loans;


}
