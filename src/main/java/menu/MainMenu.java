package menu;

import java.text.ParseException;
import java.util.Scanner;

public class MainMenu {
    static Scanner input = new Scanner(System.in);

    public static void signUpMenu() throws ParseException {
        String text = """
                1-Sign up for students
                2-Log in for students
                3-Exit
                """;
        System.out.println(text);
        switch (input.nextInt()) {
            case 1 -> SignUpMenu.signUpMenuForStudent();
            case 2 -> LogInMenu.loginMenu();
            case 3 -> System.exit(0);
            default -> System.out.println("INVALID INPUT");
        }
    }

}
