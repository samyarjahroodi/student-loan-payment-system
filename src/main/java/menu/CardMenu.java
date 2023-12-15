package menu;

import entity.card.Bank;
import entity.card.Card;
import validation.Validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import static menu.SignUpMenu.*;

public class CardMenu {
    static Scanner scanner = new Scanner(System.in);
    static Card card;

    public static void cardMenu() throws ParseException {
        card = new Card();
        String cardNumber;
        do {
            cardNumber = getInput("Card Number : ");
        } while (!Validation.cardValidation(cardNumber));
        card.setCardNumber(cardNumber);

        String expireDate;
        do {
            expireDate = getInput("Enter your expire (yyyy-MM-dd): ");
        } while (!Validation.isValidDate(expireDate));
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        card.setExpireDateOfCart(formatter.parse(expireDate));

        showBanksAndSelect();

        card.setStudent(student);


    }

    private static void showBanksAndSelect() {
        String banks = """
                1-MELLI
                2-REFAH
                3-TEJARAT
                4-MASKAN
                """;
        System.out.println(banks);
        int input = scanner.nextInt();
        switch (input) {
            case 1 -> card.setBank(Bank.MELLI);
            case 2 -> card.setBank(Bank.REFAH);
            case 3 -> card.setBank(Bank.TEJARAT);
            case 4 -> card.setBank(Bank.MASKAN);
            default -> System.out.println("invalid input");
        }
    }
}
