package entity.student;

import entity.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SuppressWarnings("unused")
public class StudentSpouse extends Person {
    @Column(name = "is_student")
    private boolean isStudent;

}
