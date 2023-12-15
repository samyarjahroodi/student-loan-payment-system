package entity.student;

import entity.Person;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@SuppressWarnings("unused")
public class StudentSpouse extends Person {
    @Column(name = "is_student", columnDefinition = "boolean default false")
    private boolean isStudent;

    @OneToOne
    private Student student;

    public StudentSpouse(String firstname, String lastname, String fatherName, String motherName, String idOfBirthCertificate, String nationalCode, Date dateOfBirth, boolean isStudent, Student student) {
        super(firstname, lastname, fatherName, motherName, idOfBirthCertificate, nationalCode, dateOfBirth);
        this.isStudent = isStudent;
        this.student = student;
    }
}
