package menu;

import service.impl.StudentServiceImpl;
import utility.ApplicationContext;

import java.util.Scanner;

public class LogInMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentServiceImpl service = ApplicationContext.getSTUDENT_SERVICE();

    public static void loginMenu() {
        while (true){
            System.out.println("Enter your national code");
            String nationalCode = scanner.nextLine();
            System.out.println("Enter your password");
            String password = scanner.nextLine();
            if (service.logIn(nationalCode, password)) {
                break;
            }
        }
    }
}
