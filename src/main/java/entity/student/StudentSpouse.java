package entity.student;

import entity.Person;
import entity.loan.Loan;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@SuppressWarnings("unused")
public class StudentSpouse extends Person {
    @Column(name = "is_student", columnDefinition = "boolean default false")
    private boolean isSheOrHeStudent;


    @OneToMany(mappedBy = "studentSpouse")
    private List<Loan> loans;

}
