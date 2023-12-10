package entity.university;

import base.entity.BaseEntity;
import entity.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SuppressWarnings("unused")
public class University extends BaseEntity<Integer> {
    @Column(name = "name")
    private String nameOfUniversity;

    private TypeOfUniversity typeOfUniversity;

    @Column(name = "is_daily")
    private Boolean isDaily;

    private String city;

    @Column(name = "has_tuition")
    private boolean hasTuition;

    @OneToMany(mappedBy = "university")
    private List<Student> students;


}
