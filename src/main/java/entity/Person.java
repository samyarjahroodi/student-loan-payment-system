package entity;

import base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
@SuppressWarnings("unused")
public class Person extends BaseEntity<Integer> {
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false, name = "father_name")
    private String fatherName;
    @Column(nullable = false, name = "mother_name")
    private String motherName;
    @Column(nullable = false, name = "id_of_birth_certificate")
    private String idOfBirthCertificate;
    @Column(nullable = false, unique = true, name = "national_code")
    private String nationalCode;
    @Column(nullable = false, unique = true, name = "date_of_birth")
    private Date dateOfBirth;
}
