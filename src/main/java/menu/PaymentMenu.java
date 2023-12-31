package menu;

import entity.card.Card;
import entity.loan.Loan;
import entity.loan.PaymentReport;
import entity.loan.TypeOfLoan;
import entity.student.Grade;
import entity.student.Student;
import service.impl.LoanCategoryServiceImpl;
import service.impl.LoanServiceImpl;
import service.impl.PaymentReportServiceImpl;
import utility.ApplicationContext;
import utility.SecurityContext;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;


@SuppressWarnings("unused")
public class PaymentMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LoanCategoryServiceImpl loanCategoryService = ApplicationContext.getLOAN_CATEGORY_SERVICE();
    private static final PaymentReportServiceImpl paymentReportService = ApplicationContext.getPAYMENT_REPORT_SERVICE();
    private static final LoanServiceImpl loanService = ApplicationContext.getLOAN_SERVICE();

    public static void paymentMenu() throws ParseException {
        showTotalAmountOfMoneyAfterInterestForTuitionLoan();
        showTotalAmountOfMoneyAfterInterestForEducationalLoan();
        showTotalAmountOfMoneyAfterInterestForHousingLoan();
        String string = """
                1-Paid installments
                2-Show Unpaid installments
                3-Pay installments
                4-Previous menu
                """;
        System.out.println(string);
        switch (scanner.nextInt()) {
            case 1 -> {
                paidInstallments();
                paymentMenu();
            }
            case 2 -> {
                unpaidInstallments();
                paymentMenu();
            }
            case 3 -> {
                payInstallments();
                paymentMenu();
            }
            case 4 -> LoanMenu.primaryMenuForLoanMenu();
            default -> System.out.println("Invalid input");

        }
    }

    private static void checkCard() throws ParseException {
        Student student = (Student) SecurityContext.getCurrentUser();
        List<Card> cards = student.getCard();

        System.out.println("Card number : ");
        String cardNumber = scanner.next();
        System.out.println("Cvv2 : ");
        int cvv2 = scanner.nextInt();
        System.out.println("Expire date : ");
        String expireDate = scanner.next();

        boolean cardMatchFound = false;

        for (Card c : cards) {
            String cardNumberOfCard = c.getCardNumber();
            int cvv2OfCard = c.getCvv2();
            LocalDate expireDateOfCard = c.getExpireDateOfCart();

            if (cardNumber.equals(cardNumberOfCard) && cvv2 == cvv2OfCard && expireDate.equals(expireDateOfCard.toString())) {
                System.out.println("This account exists in the database");
                cardMatchFound = true;
                break;
            }
        }

        if (!cardMatchFound) {
            System.out.println("No matching card found in the database");
            paymentMenu();
        }
    }


    private static LocalDate dateOfPaymentForStudent() {
        System.out.println("Enter date (yyyy-MM-dd): ");
        String input = scanner.next();
        String datePart = input.substring(input.indexOf('=') + 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(datePart, formatter);
        return parsedDate;
    }


    private static void payInstallments() throws ParseException {
        //checkCard();
        Student student = (Student) SecurityContext.getCurrentUser();
        String string = """
                1-Tuition loan
                2-Educational loan
                3-Housing loan
                """;
        System.out.println(string);
        switch (scanner.nextInt()) {
            case 1 -> {
                if (SecurityContext.getPaymentReportsForTuitionLoan() == null) {
                    List<PaymentReport> paymentBillForTuitionLoan = createPaymentBillForTuitionLoan();
                    SecurityContext.fillPaymentReportForEducationalLoan(paymentBillForTuitionLoan);
                }
                List<PaymentReport> paymentReports = SecurityContext.getPaymentReportsForTuitionLoan();
                System.out.println(paymentReportService.unpaidInstallmentsBasedOnTypeOfLoan(student, TypeOfLoan.STUDENT_TUITION_LOAN));
                System.out.println("Enter the id : ");
                int i = scanner.nextInt();
                LocalDate localDate = dateOfPaymentForStudent();
                if (i == 60) {
                    PaymentReport paymentReportBasedOnLoanNumber = paymentReportService.getPaymentReportBasedOnLoanNumber(student, i - 1);
                    LocalDate dueDate = paymentReportBasedOnLoanNumber.getDueDate();
                    if (localDate.isAfter(dueDate)) {
                        paymentReportService.payPaymentReport(student, i, TypeOfLoan.STUDENT_TUITION_LOAN, localDate);
                    }
                }
                PaymentReport paymentReportBasedOnLoanNumber = paymentReportService.getPaymentReportBasedOnLoanNumber(student, i + 1);
                LocalDate dueDate = paymentReportBasedOnLoanNumber.getDueDate();

                if (localDate.isAfter(dueDate)) {
                    System.out.println("you should choose previous payment");
                } else {
                    paymentReportService.payPaymentReport(student, i, TypeOfLoan.STUDENT_TUITION_LOAN, localDate);
                }
                paymentMenu();
            }
            case 2 -> {
                if (SecurityContext.getPaymentReportsForHousingLoan() == null) {
                    List<PaymentReport> paymentBillForEducationalLoan = createPaymentBillForEducationalLoan();
                    SecurityContext.fillPaymentReportForEducationalLoan(paymentBillForEducationalLoan);
                }
                List<PaymentReport> paymentReports = SecurityContext.getPaymentReportsForEducationalLoan();
                createPaymentBillForEducationalLoan();
                System.out.println(paymentReportService.unpaidInstallmentsBasedOnTypeOfLoan(student, TypeOfLoan.EDUCATIONAL_LOAN));
                System.out.println("Enter the id : ");
                int i = scanner.nextInt();
                LocalDate localDate = dateOfPaymentForStudent();
                if (i == 60) {
                    PaymentReport paymentReportBasedOnLoanNumber = paymentReportService.getPaymentReportBasedOnLoanNumber(student, i - 1);
                    LocalDate dueDate = paymentReportBasedOnLoanNumber.getDueDate();
                    if (localDate.isAfter(dueDate)) {
                        paymentReportService.payPaymentReport(student, i, TypeOfLoan.EDUCATIONAL_LOAN, localDate);
                        paymentMenu();
                    }
                }
                PaymentReport paymentReportBasedOnLoanNumber = paymentReportService.getPaymentReportBasedOnLoanNumber(student, i + 1);
                LocalDate dueDate = paymentReportBasedOnLoanNumber.getDueDate();

                if (localDate.isAfter(dueDate)) {
                    System.out.println("you should choose previous payment");
                } else {
                    paymentReportService.payPaymentReport(student, i, TypeOfLoan.EDUCATIONAL_LOAN, localDate);
                }
                paymentMenu();
            }
            case 3 -> {
                if (SecurityContext.getPaymentReportsForHousingLoan() == null) {
                    List<PaymentReport> paymentBillForHousingLoan = createPaymentBillForHousingLoan();
                    SecurityContext.fillPaymentReportForHousingLoan(paymentBillForHousingLoan);
                }
                List<PaymentReport> paymentReports = SecurityContext.getPaymentReportsForHousingLoan();
                System.out.println(paymentReportService.unpaidInstallmentsBasedOnTypeOfLoan(student, TypeOfLoan.HOUSING_LOAN));
                System.out.println("Enter the id : ");
                int i = scanner.nextInt();
                LocalDate localDate = dateOfPaymentForStudent();
                if (i == 60) {
                    PaymentReport paymentReportBasedOnLoanNumber = paymentReportService.getPaymentReportBasedOnLoanNumber(student, i - 1);
                    LocalDate dueDate = paymentReportBasedOnLoanNumber.getDueDate();
                    if (localDate.isAfter(dueDate)) {
                        paymentReportService.payPaymentReport(student, i, TypeOfLoan.HOUSING_LOAN, localDate);
                        paymentMenu();
                    }
                }
                PaymentReport paymentReportBasedOnLoanNumber = paymentReportService.getPaymentReportBasedOnLoanNumber(student, i + 1);
                LocalDate dueDate = paymentReportBasedOnLoanNumber.getDueDate();

                if (localDate.isAfter(dueDate)) {
                    System.out.println("you should choose previous payment");
                } else {
                    paymentReportService.payPaymentReport(student, i, TypeOfLoan.HOUSING_LOAN, localDate);
                }
                paymentMenu();
            }
            default -> System.out.println("Invalid input!!!");
        }
    }

    private static void unpaidInstallments() {
        Student student = (Student) SecurityContext.getCurrentUser();
        System.out.println(paymentReportService.unpaidInstallments(student));
        paymentReportService.unpaidInstallments(student);
    }

    private static void paidInstallments() {
        Student student = (Student) SecurityContext.getCurrentUser();
        System.out.println(paymentReportService.paidInstallments(student));
    }

    private static List<PaymentReport> createPaymentBillForTuitionLoan() {
        Student student = (Student) SecurityContext.getCurrentUser();
        Double totalAmountOfMoneyAfterInterest = totalAmountOfMoneyAfterInterestForTuitionLoan();
        double initialMoney = totalAmountOfMoneyAfterInterest / 372;

        Loan loan = loanService.loan(student, TypeOfLoan.STUDENT_TUITION_LOAN);
        List<PaymentReport> paymentReports = new ArrayList<>();
        for (int i = 1; i <= 60; i++) {
            if (i != 1 && i % 12 == 1) {
                initialMoney = initialMoney * 2;
            }
            PaymentReport paymentReport = new PaymentReport();
            paymentReport.setAmountPerPayment(initialMoney);
            paymentReport.setLoanNumber(i);
            paymentReport.setDueDate(Objects.requireNonNull(creatDates()).get(i));
            paymentReports.add(paymentReport);
            paymentReport.setLoan(loan);
            paymentReportService.saveOrUpdate(paymentReport);
        }
        SecurityContext.fillPaymentReportForTuitionLoan(paymentReports);
        return paymentReports;
    }


    private static List<PaymentReport> createPaymentBillForEducationalLoan() {
        Student student = (Student) SecurityContext.getCurrentUser();
        Double totalAmountOfMoneyAfterInterest = totalAmountOfMoneyAfterInterestForEducationalLoan();
        double initialMoney = totalAmountOfMoneyAfterInterest / 372;

        Loan loan = loanService.loan(student, TypeOfLoan.EDUCATIONAL_LOAN);
        List<PaymentReport> paymentReports = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            if (i != 1 && i % 12 == 1) {
                initialMoney = initialMoney * 2;
            }
            PaymentReport paymentReport = new PaymentReport();
            paymentReport.setAmountPerPayment(initialMoney);
            paymentReport.setLoanNumber(i);
            paymentReport.setDueDate(Objects.requireNonNull(creatDates()).get(i));
            paymentReports.add(paymentReport);
            paymentReport.setLoan(loan);
            paymentReportService.saveOrUpdate(paymentReport);
        }
        SecurityContext.fillPaymentReportForEducationalLoan(paymentReports);
        return paymentReports;
    }

    public static List<PaymentReport> createPaymentBillForHousingLoan() {
        Student student = (Student) SecurityContext.getCurrentUser();
        Double totalAmountOfMoneyAfterInterest = totalAmountOfMoneyAfterInterestForHousingLoan();
        double initialMoney = totalAmountOfMoneyAfterInterest / 372;

        Loan loan = loanService.loan(student, TypeOfLoan.HOUSING_LOAN);
        List<PaymentReport> paymentReports = new ArrayList<>();
        for (int i = 1; i <= 60; i++) {
            if (i != 1 && i % 12 == 1) {
                initialMoney = initialMoney * 2;
            }
            PaymentReport paymentReport = new PaymentReport();
            paymentReport.setAmountPerPayment(initialMoney);
            paymentReport.setLoanNumber(i);
            paymentReport.setDueDate(Objects.requireNonNull(creatDates()).get(i));
            paymentReports.add(paymentReport);
            paymentReport.setLoan(loan);
            paymentReportService.saveOrUpdate(paymentReport);
        }
        SecurityContext.fillPaymentReportForHousingLoan(paymentReports);
        return paymentReports;
    }


    private static List<LocalDate> creatDates() {
        try {
            Integer year = whenStudentIsGraduated();
            List<LocalDate> dates = new ArrayList<>();
            LocalDate currentDate = LocalDate.of(year, 3, 1);
            LocalDate endDate = currentDate.plusMonths(60);
            while (!currentDate.isAfter(endDate)) {
                dates.add(currentDate);
                currentDate = currentDate.plus(1, ChronoUnit.MONTHS);
            }
            return dates;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static Integer whenStudentIsGraduated() {
        Student student = (Student) SecurityContext.getCurrentUser();
        Grade grade = student.getGrade();
        int graduatedYear;
        if (grade.equals(Grade.DISCONTINUOUS_BACHELOR) || grade.equals(Grade.CONTINUOUS_BACHELOR)) {
            graduatedYear = 4;
            return student.getEntranceYear() + graduatedYear;
        }
        if (grade.equals(Grade.ASSOCIATE) || grade.equals(Grade.DISCONTINUOUS_MASTER)) {
            graduatedYear = 2;
            return student.getEntranceYear() + graduatedYear;
        }
        if (grade.equals(Grade.CONTINUOUS_MASTER) || grade.equals(Grade.DOCTORATE) || grade.equals(Grade.DISCONTINUOUS_SPECIALIZED_DOCTORATE) || grade.equals(Grade.CONTINUOUS_DOCTORATE)) {
            graduatedYear = 6;
            return student.getEntranceYear() + graduatedYear;
        }
        return null;
    }


    private static Double totalAmountOfMoneyAfterInterestForTuitionLoan() {
        try {
            Student student = (Student) SecurityContext.getCurrentUser();
            int i = Math.toIntExact(loanCategoryService.getAmount(student, TypeOfLoan.STUDENT_TUITION_LOAN));
            return i * 1.04;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void showTotalAmountOfMoneyAfterInterestForTuitionLoan() {
        Student student = (Student) SecurityContext.getCurrentUser();
        Long singleAmount = loanCategoryService.getAmount(student, TypeOfLoan.STUDENT_TUITION_LOAN);

        if (singleAmount != null) {
            System.out.println("Total money before interest for tuition loan : " + singleAmount);
            double amount = singleAmount * 1.04;
            int result = (int) amount;
            System.out.println("Total money after interest for tuition loan : " + result);
        } else {
            System.out.println("You didnt get any tuition loan");
        }
    }


    private static Double totalAmountOfMoneyAfterInterestForEducationalLoan() {
        try {
            Student student = (Student) SecurityContext.getCurrentUser();
            int singleAmount = Math.toIntExact(loanCategoryService.getAmount(student, TypeOfLoan.EDUCATIONAL_LOAN));
            return singleAmount * 1.04;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void showTotalAmountOfMoneyAfterInterestForEducationalLoan() {
        Student student = (Student) SecurityContext.getCurrentUser();
        Long singleAmount = loanCategoryService.getAmount(student, TypeOfLoan.EDUCATIONAL_LOAN);

        if (singleAmount != null) {
            System.out.println("Total money before interest for educational loan : " + singleAmount);
            double amount = singleAmount * 1.04;
            int result = (int) amount;
            System.out.println("Total money after interest for educational loan : " + result);
        } else {
            System.out.println("You didnt get any educational loan");
        }
    }


    private static Double totalAmountOfMoneyAfterInterestForHousingLoan() {
        try {
            Student student = (Student) SecurityContext.getCurrentUser();
            int i = Math.toIntExact(loanCategoryService.getAmount(student, TypeOfLoan.HOUSING_LOAN));
            return i * 1.04;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void showTotalAmountOfMoneyAfterInterestForHousingLoan() {
        Student student = (Student) SecurityContext.getCurrentUser();
        Long singleAmount = loanCategoryService.getAmount(student, TypeOfLoan.HOUSING_LOAN);

        if (singleAmount != null) {
            System.out.println("Total money before interest for Housing loan : " + singleAmount);
            double amount = singleAmount * 1.04;
            int result = (int) amount;
            System.out.println("Total money after interest for Housing loan : " + result);
        } else {
            System.out.println("You didnt get any Housing loan");
        }
    }
}


