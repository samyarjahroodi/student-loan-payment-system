package menu;

import service.impl.StudentServiceImpl;
import utility.ApplicationContext;

import java.text.ParseException;
import java.util.Scanner;

public class LogInMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentServiceImpl service = ApplicationContext.getSTUDENT_SERVICE();

    public static void loginMenu() throws ParseException {
        while (true) {
            System.out.println("Enter your national code");
            String nationalCode = scanner.nextLine();
            System.out.println("Enter your password");
            String password = scanner.nextLine();
            if (service.logIn(nationalCode, password)) {
                System.out.println("Login successfully");
                break;
            } else {
                System.out.println("Invalid input!");
            }
        }
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
