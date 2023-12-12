package menu;

import entity.student.Grade;
import entity.student.Student;
import entity.university.TypeOfUniversity;
import service.impl.StudentServiceImpl;
import utility.ApplicationContext;
import validation.Validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@SuppressWarnings("unused")
public class SignUpMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentServiceImpl service = ApplicationContext.getSTUDENT_SERVICE();
    static Student student = new Student();


    public static void signUpMenuForStudent() throws ParseException {
        System.out.println("---WELCOME TO SIGNUP MENU FOR STUDENT---");
        Student student = fillStudent();
        service.saveOrUpdate(student);
    }


    private static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }

    private static List<Grade> showGrade() {
        List<Grade> grades = new ArrayList<>();
        for (Grade grade : Grade.values()) {
            grades.add(grade);
        }
        return grades;
    }

    private static List<TypeOfUniversity> showTypeOfUniversities() {
        List<TypeOfUniversity> typeOfUniversities = new ArrayList<>();
        for (TypeOfUniversity t : TypeOfUniversity.values()) {
            typeOfUniversities.add(t);
        }
        return typeOfUniversities;
    }

    private static Student fillStudent() throws ParseException {

        student.setFirstname(getInput("Enter Firstname: "));
        student.setLastname(getInput("Enter Lastname: "));
        student.setFatherName(getInput("Enter Father's Name: "));
        student.setMotherName(getInput("Enter Mother's Name: "));


        String birthCertificate;
        do {
            birthCertificate = getInput("Enter ID of Birth Certificate: ");
        } while (!Validation.isValidNationalIdOfBirthCertificate(birthCertificate));
        student.setIdOfBirthCertificate(birthCertificate);

        String nationalCode;
        do {
            nationalCode = getInput("Enter National Code: ");
        } while (!Validation.isValidNationalCode(nationalCode));
        student.setNationalCode(nationalCode);

        String password;
        do {
            password = getInput("Enter your password: ");
        } while (!Validation.isValidPassword(password));
        student.setPassword(password);

        String date;
        do {
            date = getInput("Enter Date of Birth (yyyy-MM-dd): ");
        } while (!Validation.isValidDate(date));
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        student.setDateOfBirth(formatter.parse(date));

        String studentCode;
        do {
            studentCode = getInput("Enter Student Code: ");
        } while (!Validation.isValidStudentCode(studentCode));
        student.setStudentCode(studentCode);

        student.setEntranceYear(Integer.parseInt(getInput("Enter Entrance Year: ")));

        System.out.println(showGrade());
        student.setGrade(Grade.valueOf(getInput("Enter Grade: ").toUpperCase(Locale.ROOT)));

        student.setNameOfUniversity(getInput("Name of the university : "));

        System.out.println(showTypeOfUniversities());
        student.setTypeOfUniversity(TypeOfUniversity.valueOf(getInput("Type of university : ")));

        student.setIsDaily(Boolean.valueOf(getInput("Is daily : ")));

        student.setCity(getInput("City : "));
        return student;
    }


}
