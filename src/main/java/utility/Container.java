package utility;

import entity.student.Student;

@SuppressWarnings("unused")
public class Container {

    private Student student;

    public void containStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return this.student;
    }
}
