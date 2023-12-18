package menu;

import entity.student.Student;
import entity.student.StudentSpouse;
import service.impl.StudentServiceImpl;
import service.impl.StudentSpouseServiceImpl;
import utility.ApplicationContext;
import utility.SecurityContext;

import java.text.ParseException;
import java.util.Scanner;

public class LogInMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentServiceImpl studentService = ApplicationContext.getSTUDENT_SERVICE();
    private static final StudentSpouseServiceImpl spouseService = ApplicationContext.getSTUDENT_SPOUSE_SERVICE();

    public static void loginMenu() throws ParseException {
        while (true) {
            System.out.println("Enter your username");
            String nationalCode = scanner.nextLine();
            System.out.println("Enter your password");
            String password = scanner.nextLine();
            Student student;
            StudentSpouse studentSpouse;
            if (studentService.logIn(nationalCode, password)) {
                System.out.println("Login successfully");
                student = studentService.findStudentByNationalCode(nationalCode);
                SecurityContext.fillContext(student);
                menuAfterLogIn();
                break;
            } else if (spouseService.logIn(nationalCode, password)) {
                System.out.println("Login successfully");
                studentSpouse = spouseService.findStudentByNationalCode(nationalCode);
                SecurityContext.fillContext(studentSpouse);
                menuAfterLogIn();
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
    }


    public static void menuAfterLogIn() throws ParseException {
        String string = """
                1-get loan
                2-loan payment
                3-exit
                """;
        System.out.println(string);
        switch (scanner.nextInt()) {
            case 1 -> LoanMenu.primaryMenuForLoanMenu();
            case 2 -> PaymentMenu.paymentMenu();
            case 3 -> System.exit(0);
            default -> System.out.println("invalid input");
        }
    }

}
