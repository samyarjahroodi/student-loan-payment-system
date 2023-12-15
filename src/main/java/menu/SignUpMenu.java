package menu;

import entity.card.Card;
import entity.student.Grade;
import entity.student.Student;
import entity.student.StudentSpouse;
import entity.university.TypeOfGovernmentalUniversity;
import entity.university.TypeOfUniversity;
import service.impl.CardServiceImpl;
import service.impl.StudentServiceImpl;
import service.impl.StudentSpouseServiceImpl;
import utility.ApplicationContext;
import validation.Validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("unused")
public class SignUpMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentServiceImpl service = ApplicationContext.getSTUDENT_SERVICE();
    private static final StudentSpouseServiceImpl studentSpouseService = ApplicationContext.getSTUDENT_SPOUSE_SERVICE();
    private static final CardServiceImpl cardService = ApplicationContext.getCARD_SERVICE();
    static StudentSpouse studentSpouse;
    static Student student = new Student();
    static Card card = new Card();


    public static void signUpMenuForStudent() throws ParseException {
        System.out.println("---WELCOME TO SIGNUP MENU FOR STUDENT---");
        fillStudent();
//        service.saveOrUpdate(student);
    }


    public static String getInput(String prompt) {
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

    //to do avoid duplicate methods
    private static void fillStudent() throws ParseException {

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

        String date;
        do {
            date = getInput("Enter your Date of Birth (yyyy-MM-dd): ");
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


        String password;
        do {
            password = getInput("Enter your password: ");
        } while (!Validation.isValidPassword(password));
        student.setPassword(password);

        giveSpouse();

        student.setNameOfUniversity(getInput("Name of the university : "));

        student.setAccommodateInUniversity(Boolean.parseBoolean(getInput("Accommodate In University : ")));

        System.out.println(showTypeOfUniversities());
        TypeOfUniversity typeOfUniversity = TypeOfUniversity.valueOf(getInput("Type of university : "));
        student.setTypeOfUniversity(typeOfUniversity);

        if (typeOfUniversity.equals(TypeOfUniversity.GOVERNMENTAL)) {
            addTypeOfGovernmentalUniversity();
        } else {
            TypeOfGovernmentalUniversity.values().equals(null);
        }

        student.setCity(getInput("City : "));

        service.saveOrUpdate(student);
    }


    private static void addTypeOfGovernmentalUniversity() {
        System.out.println("Which type you are : ");
        String string = """
                1-Daily
                2-nightly
                """;
        System.out.println(string);
        int input = scanner.nextInt();
        switch (input) {
            case 1 -> student.setTypeOfGovernmentalUniversity(TypeOfGovernmentalUniversity.DAILY);
            case 2 -> student.setTypeOfGovernmentalUniversity(TypeOfGovernmentalUniversity.NIGHTLY);
            default -> System.out.println("Invalid input");
        }
    }


    private static List<TypeOfGovernmentalUniversity> showTypes() {
        List<TypeOfGovernmentalUniversity> typeOfGovernmentalUniversities = new ArrayList<>();
        for (TypeOfGovernmentalUniversity t : TypeOfGovernmentalUniversity.values()) {
            typeOfGovernmentalUniversities.add(t);
        }
        return typeOfGovernmentalUniversities;
    }


    private static void isSpouseStudent() {
        String string = """
                Is your spouse student:
                1-true
                2-false
                """;
        System.out.println(string);
        int input = scanner.nextInt();
        switch (input) {
            case 1 -> studentSpouse.setStudent(true);
            case 2 -> studentSpouse.setStudent(false);
            default -> System.out.println("invalid input");
        }
    }


    private static StudentSpouse giveSpouse() throws ParseException {
        String isMarried = """
                is Married :
                1-true
                2-false
                """;
        System.out.println(isMarried);
        int input = scanner.nextInt();
        switch (input) {
            case 1 -> {
                studentSpouse = new StudentSpouse();
                student.setMarried(true);
                System.out.println("Spouse information : ");
                studentSpouse.setFirstname(getInput("Enter spouse firstname: "));
                studentSpouse.setLastname(getInput("Enter spouse lastname: "));
                studentSpouse.setFatherName(getInput("Enter spouse Father's Name: "));
                studentSpouse.setMotherName(getInput("Enter spouse mother Name: "));


                String birthCertificate;
                do {
                    birthCertificate = getInput("Enter ID of Birth Certificate: ");
                } while (!Validation.isValidNationalIdOfBirthCertificate(birthCertificate));
                studentSpouse.setIdOfBirthCertificate(birthCertificate);

                String nationalCode;
                do {
                    nationalCode = getInput("Enter National Code: ");
                } while (!Validation.isValidNationalCode(nationalCode));
                studentSpouse.setNationalCode(nationalCode);


                String date;
                do {
                    date = getInput("Enter Date of Birth (yyyy-MM-dd): ");
                } while (!Validation.isValidDate(date));

                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                studentSpouse.setDateOfBirth(formatter.parse(date));
                isSpouseStudent();
                studentSpouseService.saveOrUpdate(studentSpouse);
            }
            case 2 -> student.setMarried(false);
        }
    }
}
