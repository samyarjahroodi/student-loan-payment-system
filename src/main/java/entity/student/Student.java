package entity.student;

import entity.Person;
import entity.card.Card;
import entity.loan.Loan;
import entity.university.TypeOfGovernmentalUniversity;
import entity.university.TypeOfUniversity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SuppressWarnings("unused")
public class Student extends Person {

    @Column(unique = true, name = "student_code")
    private String studentCode;

    @Column(nullable = false, name = "entrance_year")
    @Max(value = 1403, message = "incorrect input value")
    @Min(value = 1399, message = "incorrect input value")
    private Integer entranceYear;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(name = "is_married")
    private boolean isMarried;

    private boolean accommodateInUniversity;

    @Column(name = "name")
    private String nameOfUniversity;

    @Enumerated(EnumType.STRING)
    private TypeOfUniversity typeOfUniversity;

    @Enumerated(EnumType.STRING)
    private TypeOfGovernmentalUniversity typeOfGovernmentalUniversity;

    private String city;

    @OneToOne
    private StudentSpouse studentSpouse;

    @OneToMany(mappedBy = "student")
    private List<Card> card;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Loan> loans = new ArrayList<>();
}
