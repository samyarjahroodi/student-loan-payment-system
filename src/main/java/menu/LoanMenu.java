package menu;


import entity.card.Card;
import entity.loan.*;
import entity.student.Grade;
import entity.student.Student;
import entity.student.StudentSpouse;
import entity.university.TypeOfGovernmentalUniversity;
import entity.university.TypeOfUniversity;
import service.impl.CardServiceImpl;
import service.impl.LoanCategoryServiceImpl;
import service.impl.LoanServiceImpl;
import utility.ApplicationContext;
import utility.SecurityContext;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@SuppressWarnings("unused")
public class LoanMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LoanServiceImpl loanService = ApplicationContext.getLOAN_SERVICE();
    private static final LoanCategoryServiceImpl loanCategoryService = ApplicationContext.getLOAN_CATEGORY_SERVICE();
    private static final CardServiceImpl cardService = ApplicationContext.getCARD_SERVICE();


    public static void primaryMenuForLoanMenu() throws ParseException {
        String string = """
                1-tuition loan
                2-education loan
                3-housing loan
                3-login menu
                4-previous menu
                5-exit
                """;
        System.out.println(string);
        switch (scanner.nextInt()) {
            case 1 -> tuitionLoan();
            case 2 -> educationLoan();
            case 3 -> housingLoan();
            case 4 -> LogInMenu.menuAfterLogIn();
            case 5 -> System.exit(0);
            default -> System.out.println("Invalid input!");
        }
    }


    public static Boolean checkIfStudentIsGraduated() throws ParseException {
        Student student = (Student) SecurityContext.getCurrentUser();
        Grade grade = student.getGrade();
        int graduatedYear;
        LocalDate date = getDate();
        int year = date.getYear();
        if (grade.equals(Grade.DISCONTINUOUS_BACHELOR) || grade.equals(Grade.CONTINUOUS_BACHELOR)) {
            graduatedYear = 4;
            int timeThatStudentShouldBeGraduated = student.getEntranceYear() + graduatedYear;
            if (year > timeThatStudentShouldBeGraduated) {
                System.out.println("you are graduated you cannot get any loan!");
                return true;
            } else {
                return false;
            }
        }
        if (grade.equals(Grade.ASSOCIATE) || grade.equals(Grade.DISCONTINUOUS_MASTER)) {
            graduatedYear = 2;
            int timeThatStudentShouldBeGraduated = student.getEntranceYear() + graduatedYear;
            if (year > timeThatStudentShouldBeGraduated) {
                System.out.println("you are graduated you cannot get any loan!");
                return true;
            } else {
                return false;
            }
        }
        if (grade.equals(Grade.CONTINUOUS_MASTER) || grade.equals(Grade.DOCTORATE) || grade.equals(Grade.DISCONTINUOUS_SPECIALIZED_DOCTORATE)) {
            graduatedYear = 6;
            int timeThatStudentShouldBeGraduated = student.getEntranceYear() + graduatedYear;
            if (year > timeThatStudentShouldBeGraduated) {
                System.out.println("you are graduated you cannot get any loan!");
                return true;
            } else {
                return false;
            }
        }
        return null;
    }


    private static LocalDate getDate() {

        String inputPrompt = """
                You can get a loan from 1TH of ABAN to 7TH of ABAN
                          or
                from 25TH of BAHMAN to 2TH of ESFAND
                ---your pattern should match "yyyy-MM-dd"
                """;
        System.out.println(inputPrompt);

        String dateString = scanner.next();
        isValidDateInOrderToGetLoan(dateString);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parse = LocalDate.parse(dateString, formatter);
        SecurityContext.fillContext(parse);
        return parse;
    }


    public static void tuitionLoan() throws ParseException {
        if (Boolean.TRUE.equals(checkIfStudentIsGraduated())) {
            primaryMenuForLoanMenu();
        }
        LocalDate todayDate = SecurityContext.getTodayDate();
        Student student = (Student) SecurityContext.getCurrentUser();
        if (studentHasActiveTuitionLoan(student)) {
            System.out.println("you have already taken this loan in current term");
            primaryMenuForLoanMenu();
        }
        LoanCategory loanCategory = new LoanCategory();
        Loan loan = new Loan();
        loan.setDateThatLoanHasBeenGet(todayDate);
        if ((student.getTypeOfUniversity().equals(TypeOfUniversity.GOVERNMENTAL) &&
                student.getTypeOfGovernmentalUniversity().equals(TypeOfGovernmentalUniversity.DAILY))
        ) {
            System.out.println("you can not get tuition loan !!!!");
            primaryMenuForLoanMenu();
        }
        loanCategory.setTypeOfLoan(TypeOfLoan.STUDENT_TUITION_LOAN);
        loanCategory.setPaymentType(PaymentType.ONE_EACH_TERM);
        Grade grade = student.getGrade();
        if (grade.equals(Grade.ASSOCIATE) || grade.equals(Grade.CONTINUOUS_BACHELOR) ||
                grade.equals(Grade.DISCONTINUOUS_BACHELOR)) {
            long l = 1900000L;
            loanCategory.setAmount(l);
            loanCategoryService.saveOrUpdate(loanCategory);
            loan.setLoanCategory(loanCategory);
            loan.setStudent(student);
            loanService.saveOrUpdate(loan);
            Card card = setBankForPayment();
            assert card != null;
            if (card.getAmountOfAccount() == null) {
                card.setAmountOfAccount(l);
            } else {
                card.setAmountOfAccount(card.getAmountOfAccount() + l);
            }

            cardService.saveOrUpdate(card);
        } else if (grade.equals(Grade.CONTINUOUS_MASTER)
                || grade.equals(Grade.DISCONTINUOUS_MASTER)
                || grade.equals(Grade.DOCTORATE) || grade.equals(Grade.CONTINUOUS_DOCTORATE)) {
            long l = 2250000L;
            loanCategory.setAmount(l);
            loanCategoryService.saveOrUpdate(loanCategory);
            loan.setLoanCategory(loanCategory);
            loan.setStudent(student);
            loanService.saveOrUpdate(loan);
            Card card = setBankForPayment();
            assert card != null;
            if (card.getAmountOfAccount() == null) {
                card.setAmountOfAccount(l);
            } else {
                card.setAmountOfAccount(card.getAmountOfAccount() + l);
            }
            cardService.saveOrUpdate(card);
        } else if (grade.equals(Grade.DISCONTINUOUS_SPECIALIZED_DOCTORATE)) {
            long l = 2600000L;
            loanCategory.setAmount(2600000L);
            loanCategoryService.saveOrUpdate(loanCategory);
            loan.setLoanCategory(loanCategory);
            loan.setStudent(student);
            loanService.saveOrUpdate(loan);
            Card card = setBankForPayment();
            assert card != null;
            if (card.getAmountOfAccount() == null) {
                card.setAmountOfAccount(l);
            } else {
                card.setAmountOfAccount(card.getAmountOfAccount() + l);
            }
            cardService.saveOrUpdate(card);
        }
        LogInMenu.menuAfterLogIn();
    }


    private static void educationLoan() throws ParseException {
        if (Boolean.TRUE.equals(checkIfStudentIsGraduated())) {
            primaryMenuForLoanMenu();
        }
        LocalDate todayDate = SecurityContext.getTodayDate();
        Student student = (Student) SecurityContext.getCurrentUser();
        if (studentHasActiveEducationalLoan(student)) {
            System.out.println("you have already taken this loan in current term");
            primaryMenuForLoanMenu();
        }
        Loan loan = new Loan();
        LoanCategory loanCategory = new LoanCategory();
        loanCategory.setTypeOfLoan(TypeOfLoan.EDUCATIONAL_LOAN);
        loanCategory.setPaymentType(PaymentType.ONE_EACH_TERM);
        loan.setDateThatLoanHasBeenGet(todayDate);
        Grade grade = student.getGrade();

        if (grade.equals(Grade.ASSOCIATE) || grade.equals(Grade.CONTINUOUS_BACHELOR) ||
                grade.equals(Grade.DISCONTINUOUS_BACHELOR)) {
            long l = 1300000L;
            loanCategory.setAmount(l);
            loanCategoryService.saveOrUpdate(loanCategory);
            loan.setLoanCategory(loanCategory);
            loan.setStudent(student);
            loanService.saveOrUpdate(loan);
            Card card = setBankForPayment();
            assert card != null;
            if (card.getAmountOfAccount() == null) {
                card.setAmountOfAccount(l);
            } else {
                card.setAmountOfAccount(card.getAmountOfAccount() + l);
            }
            cardService.saveOrUpdate(card);
        } else if (grade.equals(Grade.CONTINUOUS_MASTER)
                || grade.equals(Grade.DISCONTINUOUS_MASTER)
                || grade.equals(Grade.DOCTORATE) || grade.equals(Grade.CONTINUOUS_DOCTORATE)) {
            long l = 2600000L;
            loanCategory.setAmount(l);
            loanCategoryService.saveOrUpdate(loanCategory);
            loan.setLoanCategory(loanCategory);
            loan.setStudent(student);
            loanService.saveOrUpdate(loan);
            Card card = setBankForPayment();
            assert card != null;
            if (card.getAmountOfAccount() == null) {
                card.setAmountOfAccount(l);
            } else {
                card.setAmountOfAccount(card.getAmountOfAccount() + l);
            }
            cardService.saveOrUpdate(card);
        } else if (grade.equals(Grade.DISCONTINUOUS_SPECIALIZED_DOCTORATE)) {
            long l = 6500000L;
            loanCategory.setAmount(l);
            loanCategoryService.saveOrUpdate(loanCategory);
            loan.setLoanCategory(loanCategory);
            loan.setStudent(student);
            loanService.saveOrUpdate(loan);
            Card card = setBankForPayment();
            assert card != null;
            if (card.getAmountOfAccount() == null) {
                card.setAmountOfAccount(l);
            } else {
                card.setAmountOfAccount(card.getAmountOfAccount() + l);
            }
            cardService.saveOrUpdate(card);
        }
        System.out.println("SUCCESSFULLY ADDED");
        LogInMenu.menuAfterLogIn();
    }


    private static Card setBankForPayment() {
        Student student = (Student) SecurityContext.getCurrentUser();
        List<Card> card1 = student != null ? student.getCard() : Collections.emptyList();
        HashMap<Integer, Card> cards = new HashMap<>();
        int i = 0;

        for (Card c : card1) {
            cards.put(i++, c);
        }
        System.out.println(cards);
        if (!cards.isEmpty()) {
            System.out.println("Which card do you want to put money into : ");
            int i1 = scanner.nextInt();
            return cards.get(i1);
        } else {
            return null;
        }
    }


    private static void housingLoan() throws ParseException {
        Student student = (Student) SecurityContext.getCurrentUser();
        LocalDate todayDate = SecurityContext.getTodayDate();
        if (student == null) {
            StudentSpouse studentSpouse = (StudentSpouse) SecurityContext.getCurrentUser();
        }
        Loan loan = new Loan();
        LoanCategory loanCategory = new LoanCategory();

        if (loanCategory.getHousingRentalAgreementNumber() != null ||
                Objects.requireNonNull(student).getStudentSpouse() == null || student.isAccommodateInUniversity()
        ) {
            System.out.println("You are not allowed to get housing loan");
            primaryMenuForLoanMenu();
        } else {
            String housingRentalNumber = """
                    Please enter your housing rental number
                    """;
            System.out.println(housingRentalNumber);
            loanCategory.setHousingRentalAgreementNumber(scanner.next());
        }
        loanCategory.setTypeOfLoan(TypeOfLoan.HOUSING_LOAN);
        loanCategory.setPaymentType(PaymentType.ONE_EACH_LEVEL);
        loan.setDateThatLoanHasBeenGet(todayDate);
        String capital = "TEHRAN";
        assert student != null;
        boolean equals = student.getCity().equals(capital);
        String studentCity = student.getCity();
        if (studentCity != null) {
            boolean bigCities = false;
            for (BigCities b : BigCities.values()) {
                if (studentCity.equals(b.name())) {
                    bigCities = true;
                    break;
                }
            }
            if (bigCities) {
                long l = 26000000L;
                loanCategory.setAmount(l);
                loanCategoryService.saveOrUpdate(loanCategory);
                loan.setLoanCategory(loanCategory);
                loan.setStudent(student);
                loanService.saveOrUpdate(loan);
                Card card = setBankForPayment();
                assert card != null;
                if (card.getAmountOfAccount() == null) {
                    card.setAmountOfAccount(l);
                } else {
                    card.setAmountOfAccount(card.getAmountOfAccount() + l);
                }

                cardService.saveOrUpdate(card);
            } else if (equals) {
                Long l = 32000000L;
                loanCategory.setAmount(l);
                loanCategoryService.saveOrUpdate(loanCategory);
                loan.setLoanCategory(loanCategory);
                loan.setStudent(student);
                loanService.saveOrUpdate(loan);
                Card card = setBankForPayment();
                assert card != null;
                if (card.getAmountOfAccount() == null) {
                    card.setAmountOfAccount(l);
                } else {
                    card.setAmountOfAccount(card.getAmountOfAccount() + l);
                }
                cardService.saveOrUpdate(card);
            } else {
                long l = 19500000L;
                loanCategory.setAmount(l);
                loanCategoryService.saveOrUpdate(loanCategory);
                loan.setLoanCategory(loanCategory);
                loan.setStudent(student);
                loanService.saveOrUpdate(loan);
                Card card = setBankForPayment();
                assert card != null;
                if (card.getAmountOfAccount() == null) {
                    card.setAmountOfAccount(l);
                } else {
                    card.setAmountOfAccount(card.getAmountOfAccount() + l);
                }

                cardService.saveOrUpdate(card);
            }
        }
        System.out.println("SUCCESSFULLY ADDED");

        LogInMenu.menuAfterLogIn();
    }


    public static boolean studentHasActiveTuitionLoan(Student student) {
        List<Loan> loans = student.getLoans();
        if (!loans.isEmpty()) {
            try {
                for (Loan loan : loans) {
                    if (loan.getLoanCategory().getTypeOfLoan().equals(TypeOfLoan.STUDENT_TUITION_LOAN) &&
                            loan.getDateThatLoanHasBeenGet().getYear() == SecurityContext.getTodayDate().getYear())
                        return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public static boolean studentHasActiveEducationalLoan(Student student) {
        List<Loan> loans = student.getLoans();
        if (!loans.isEmpty()) {
            try {
                for (Loan loan : loans) {
                    if (loan.getLoanCategory().getTypeOfLoan().equals(TypeOfLoan.EDUCATIONAL_LOAN) &&
                            loan.getDateThatLoanHasBeenGet().getYear() == SecurityContext.getTodayDate().getYear())
                        return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public static void isValidDateInOrderToGetLoan(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate userDate = LocalDate.parse(date, formatter);

        int startYear = 1399;
        int endYear = 1410;

        for (int year = startYear; year <= endYear; year++) {
            LocalDate startDate1 = LocalDate.of(year, 8, 1);
            LocalDate endDate1 = LocalDate.of(year, 8, 7);

            LocalDate startDate2 = LocalDate.of(year, 10, 25);
            LocalDate endDate2 = LocalDate.of(year, 11, 2);
            try {
                if ((userDate.isEqual(startDate1) || userDate.isAfter(startDate1)) &&
                        (userDate.isEqual(endDate1) || userDate.isBefore(endDate1)) ||
                        (userDate.isEqual(startDate2) || userDate.isAfter(startDate2)) &&
                                (userDate.isEqual(endDate2) || userDate.isBefore(endDate2))) {
                    System.out.println("Loan can be obtained because of the date but it needs more information!!!! ");
                    SecurityContext.fillContext(userDate);
                    return;
                }
            } catch (IllegalArgumentException exception) {
                throw new IllegalArgumentException("Loan can only be obtained between " +
                        "1402-08-01 and 1402-08-07 or 1402-10-25 and 1402-11-02 or after 1405-08-01 and before 1405-08-07 or during 1410");
            }
        }
    }
}
