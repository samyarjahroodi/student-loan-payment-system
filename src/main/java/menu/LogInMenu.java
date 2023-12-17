package menu;

import entity.student.Student;
import service.impl.StudentServiceImpl;
import utility.ApplicationContext;
import utility.SecurityContext;

import java.text.ParseException;
import java.util.Scanner;

public class LogInMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentServiceImpl service = ApplicationContext.getSTUDENT_SERVICE();

    public static void loginMenu() throws ParseException {
        while (true) {
            System.out.println("Enter your username");
            String nationalCode = scanner.nextLine();
            System.out.println("Enter your password");
            String password = scanner.nextLine();
            Student student;
            if (service.logIn(nationalCode, password)) {
                System.out.println("Login successfully");
                student = service.findStudentByNationalCode(nationalCode);
                SecurityContext.fillContext(student);
                menuAfterLogIn();
                break;
            } else {
                System.out.println("Invalid input!");
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
