package menu;

import entity.card.Card;

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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static menu.ShowEnums.*;

@SuppressWarnings("unused")
public class SignUpMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentServiceImpl service = ApplicationContext.getSTUDENT_SERVICE();
    private static final StudentSpouseServiceImpl studentSpouseService = ApplicationContext.getSTUDENT_SPOUSE_SERVICE();
    private static final CardServiceImpl cardService = ApplicationContext.getCARD_SERVICE();
    static StudentSpouse studentSpouse = new StudentSpouse();
    static Student student = new Student();
    static Card card = new Card();


    public static void signUpMenuForStudent() throws ParseException {
        System.out.println("---WELCOME TO SIGNUP MENU FOR STUDENT---");
        fillStudent();
    }


    public static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }


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
            nationalCode = getInput("Enter National Code (username): ");
        } while (!Validation.isValidNationalCode(nationalCode));
        student.setNationalCode(nationalCode);

        student.setUsername(student.getNationalCode());

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


        String password = Validation.generateRandomPassword();
        System.out.println("Your random password is: " + password);
        student.setPassword(password);

        showGrade();

        giveSpouse();

        student.setNameOfUniversity(getInput("Name of the university : "));

        accommodateInUniversity();

        TypeOfUniversity typeOfUniversity = showTypeOfUniversities();

        if (typeOfUniversity.equals(TypeOfUniversity.GOVERNMENTAL)) {
            addTypeOfGovernmentalUniversity();
        } else {
            student.setTypeOfGovernmentalUniversity(TypeOfGovernmentalUniversity.NULL);
        }

        student.setCity(getInput("City : ").toUpperCase(Locale.ROOT));

        cardMenu();

        service.saveOrUpdate(student);
        System.out.println("SUCCESSFUL!!!");
        MainMenu.signUpMenu();
    }

    private static void accommodateInUniversity() {
        String string = """
                Accommodate in university :
                1-true;
                2-false
                """;
        System.out.println(string);
        int input = scanner.nextInt();
        switch (input) {
            case 1 -> student.setAccommodateInUniversity(true);
            case 2 -> student.setAccommodateInUniversity(false);
            default -> System.out.println("Invalid input");
        }
    }


    private static void cardMenu() throws ParseException {
        String cardNumber;
        do {
            cardNumber = getInput("Card Number : ");
        } while (!Validation.cardValidation(cardNumber));
        card.setCardNumber(cardNumber);

        String expireDate;
        do {
            expireDate = getInput("Enter your expire date (yyyy-MM-dd): ");
        } while (!Validation.isValidDate(expireDate));
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = formatter.parse(expireDate);
        Instant instant = Instant.ofEpochMilli(parsedDate.getTime());
        card.setExpireDateOfCart(LocalDate.ofInstant(instant, ZoneId.systemDefault()));

        String cvv2;
        do {
            cvv2 = getInput("Enter your expire cvv2: ");
        } while (!Validation.cvv2Validation(cvv2));
        card.setCvv2(Integer.parseInt(cvv2));

        showBanksAndSelect();

        cardService.saveOrUpdate(card);

        card.setStudent(student);
    }

    public static void addTypeOfGovernmentalUniversity() {
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


    private static void giveSpouse() throws ParseException {
        String isMarried = """
                is Married? :
                1-true
                2-false
                """;
        System.out.println(isMarried);
        int input = scanner.nextInt();
        switch (input) {
            case 1 -> {
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
                    nationalCode = getInput("Enter National Code (username): ");
                } while (!Validation.isValidNationalCode(nationalCode));
                studentSpouse.setNationalCode(nationalCode);
                studentSpouse.setUsername(studentSpouse.getNationalCode());

                String password = Validation.generateRandomPassword();
                System.out.println("Your random password is : " + password);
                studentSpouse.setPassword(password);

                String date;
                do {
                    date = getInput("Enter Date of Birth (yyyy-MM-dd): ");
                } while (!Validation.isValidDate(date));

                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                studentSpouse.setDateOfBirth(formatter.parse(date));
                String string = """
                        Is your spouse student:
                        1-true
                        2-false
                        """;
                System.out.println(string);
                int input1 = scanner.nextInt();
                switch (input1) {
                    case 1 -> studentSpouse.setSheOrHeStudent(true);
                    case 2 -> studentSpouse.setSheOrHeStudent(false);
                    default -> System.out.println("invalid input");
                }
                studentSpouseService.saveOrUpdate(studentSpouse);
                student.setStudentSpouse(studentSpouse);
            }
            case 2 -> student.setMarried(false);
        }
    }
}
