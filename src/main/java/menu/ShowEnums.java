package menu;

import entity.card.Bank;
import entity.student.Grade;
import entity.university.TypeOfUniversity;

import java.util.Scanner;

import static menu.SignUpMenu.card;
import static menu.SignUpMenu.student;

public class ShowEnums {
    static Scanner scanner = new Scanner(System.in);

    public static void showGrade() {
        String string = """
                     1-ASSOCIATE,
                     2-CONTINUOUS_BACHELOR,
                     3-DISCONTINUOUS_BACHELOR,
                     4-CONTINUOUS_MASTER,
                     5-DISCONTINUOUS_MASTER,
                     6-DOCTORATE,
                     7-CONTINUOUS_DOCTORATE,
                     8-DISCONTINUOUS_SPECIALIZED_DOCTORATE 
                """;
        System.out.println(string);
        int input = scanner.nextInt();
        switch (input) {
            case 1 -> student.setGrade(Grade.ASSOCIATE);
            case 2 -> student.setGrade(Grade.CONTINUOUS_BACHELOR);
            case 3 -> student.setGrade(Grade.DISCONTINUOUS_BACHELOR);
            case 4 -> student.setGrade(Grade.CONTINUOUS_MASTER);
            case 5 -> student.setGrade(Grade.DISCONTINUOUS_MASTER);
            case 6 -> student.setGrade(Grade.DOCTORATE);
            case 7 -> student.setGrade(Grade.CONTINUOUS_DOCTORATE);
            case 8 -> student.setGrade(Grade.DISCONTINUOUS_SPECIALIZED_DOCTORATE);
            default -> System.out.println("Invalid input");
        }
    }

    public static void showBanksAndSelect() {
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


    public static TypeOfUniversity showTypeOfUniversities() {
        String typeOfUniversities = """
                1-GOVERNMENTAL,
                2-GHEYRENTEFAYI,
                3-PARDIS,
                4-ZARFIATMAZAD,
                5-PAYAMNOOR,
                6-ELMIKARBORDI,
                7-AZAD
                """;
        System.out.println(typeOfUniversities);
        int input = scanner.nextInt();
        TypeOfUniversity typeOfUniversity = null;
        switch (input) {
            case 1 -> typeOfUniversity = typeOfUniversity.GOVERNMENTAL;
            case 2 -> typeOfUniversity = typeOfUniversity.GHEYRENTEFAYI;
            case 3 -> typeOfUniversity = typeOfUniversity.PARDIS;
            case 4 -> typeOfUniversity = typeOfUniversity.ZARFIATMAZAD;
            case 5 -> typeOfUniversity = typeOfUniversity.PAYAMNOOR;
            case 6 -> typeOfUniversity = typeOfUniversity.ELMIKARBORDI;
            case 7 -> typeOfUniversity = typeOfUniversity.AZAD;
            default -> System.out.println("Invalid input");
        }
        student.setTypeOfUniversity(typeOfUniversity);
        return typeOfUniversity;

    }
}
