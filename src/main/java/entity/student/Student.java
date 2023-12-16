package entity.student;

import entity.Person;
import entity.card.Card;
import entity.loan.Loan;
import entity.university.TypeOfGovernmentalUniversity;
import entity.university.TypeOfUniversity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private Integer entranceYear;

    @Column(nullable = false)
    private Grade grade;

    private String password;

    @Column(name = "is_married")
    private boolean isMarried;

    private boolean accommodateInUniversity;

    @Column(name = "name")
    private String nameOfUniversity;

    private TypeOfUniversity typeOfUniversity;

    private TypeOfGovernmentalUniversity typeOfGovernmentalUniversity;

    private String city;

//    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
//    private Set<Card> cards;

    @OneToOne
    private StudentSpouse studentSpouse;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Loan> loans;

    public Student(String firstname, String lastname, String fatherName, String motherName,
                   String idOfBirthCertificate, String nationalCode, Date dateOfBirth,
                   String studentCode, Integer entranceYear, Grade grade, String password,
                   boolean isMarried, boolean accommodateInUniversity, String nameOfUniversity,
                   TypeOfUniversity typeOfUniversity, TypeOfGovernmentalUniversity typeOfGovernmentalUniversity, String city,
                   StudentSpouse studentSpouse) {
        super(firstname, lastname, fatherName, motherName, idOfBirthCertificate, nationalCode, dateOfBirth);
        this.studentCode = studentCode;
        this.entranceYear = entranceYear;
        this.grade = grade;
        this.password = password;
        this.isMarried = isMarried;
        this.accommodateInUniversity = accommodateInUniversity;
        this.nameOfUniversity = nameOfUniversity;
        this.typeOfUniversity = typeOfUniversity;
        this.typeOfGovernmentalUniversity = typeOfGovernmentalUniversity;
        this.city = city;
        this.studentSpouse = studentSpouse;
    }
}
