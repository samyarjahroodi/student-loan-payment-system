package menu;

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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@SuppressWarnings("unused")
public class PaymentMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LoanCategoryServiceImpl loanCategoryService = ApplicationContext.getLOAN_CATEGORY_SERVICE();
    private static final PaymentReportServiceImpl paymentReportService = ApplicationContext.getPAYMENT_REPORT_SERVICE();
    private static final LoanServiceImpl loanService = ApplicationContext.getLOAN_SERVICE();
    private static final PaymentReport paymentReport = new PaymentReport();

    public static void paymentMenu() throws ParseException {
        createPaymentBillForTuitionLoan();
        createPaymentBillForEducationalLoan();
        createPaymentBillForHousingLoan();
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

    private static void payInstallments() throws ParseException {
        Student student = (Student) SecurityContext.getCurrentUser();
        String string = """
                1-Tuition loan
                2-Educational loan
                3-Housing loan
                """;
        System.out.println(string);
        switch (scanner.nextInt()) {
            case 1 -> {
                System.out.println(paymentReportService.unpaidInstallmentsBasedOnTypeOfLoan(student, TypeOfLoan.STUDENT_TUITION_LOAN));
                System.out.println("Enter the id : ");
                paymentReportService.payPaymentReport(student, scanner.nextInt(), TypeOfLoan.STUDENT_TUITION_LOAN);
                paymentMenu();
            }
            case 2 -> {
                System.out.println(paymentReportService.unpaidInstallmentsBasedOnTypeOfLoan(student, TypeOfLoan.EDUCATIONAL_LOAN));
                System.out.println("Enter the id : ");
                paymentReportService.payPaymentReport(student, scanner.nextInt(), TypeOfLoan.STUDENT_TUITION_LOAN);
                paymentMenu();
            }
            case 3 -> {
                System.out.println(paymentReportService.unpaidInstallmentsBasedOnTypeOfLoan(student, TypeOfLoan.HOUSING_LOAN));
                System.out.println("Enter the id : ");
                paymentReportService.payPaymentReport(student, scanner.nextInt(), TypeOfLoan.STUDENT_TUITION_LOAN);
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

    private static void createPaymentBillForTuitionLoan() {
        Student student = (Student) SecurityContext.getCurrentUser();
        Integer totalAmountOfMoneyAfterInterest = totalAmountOfMoneyAfterInterestForTuitionLoan();
        int initialMoney = totalAmountOfMoneyAfterInterest / 372;

        Loan loan = loanService.loan(student, TypeOfLoan.STUDENT_TUITION_LOAN);
        List<PaymentReport> paymentReports = new ArrayList<>();
        for (int i = 1; i <= 60; i++) {
            Double aDouble = paymentReportService.totalAmountOfPayments(paymentReports);
            if (aDouble > totalAmountOfMoneyAfterInterest) {
                double remain = totalAmountOfMoneyAfterInterest - aDouble;
                PaymentReport paymentReport = new PaymentReport();
                paymentReport.setId(i);
                paymentReport.setAmountPerPayment(aDouble);
                paymentReport.setLoanNumber(i);
                paymentReports.add(paymentReport);
                paymentReport.setLoan(loan);
                paymentReportService.saveOrUpdate(paymentReport);
                break;
            }
            if (i % 12 == 0) {
                initialMoney = initialMoney * 2;
            }
            PaymentReport paymentReport = new PaymentReport();
            paymentReport.setId(i);
            paymentReport.setAmountPerPayment((double) initialMoney);
            paymentReport.setLoanNumber(i);
            paymentReports.add(paymentReport);
            paymentReport.setLoan(loan);
            paymentReportService.saveOrUpdate(paymentReport);
        }
    }


    private static List<LocalDate> creatDates() {
        try {
            Integer year = whenStudentIsGraduated();
            List<LocalDate> dates = new ArrayList<>();
            LocalDate currentDate = LocalDate.of(year, 3, 21);
            LocalDate endDate = currentDate.plusMonths(18);
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

    private static void createPaymentBillForHousingLoan() {
        Student student = (Student) SecurityContext.getCurrentUser();
        Integer totalAmountOfMoneyAfterInterest = totalAmountOfMoneyAfterInterestForTuitionLoan();
        int initialMoney = totalAmountOfMoneyAfterInterest / 372;

        Loan loan = loanService.loan(student, TypeOfLoan.HOUSING_LOAN);
        List<PaymentReport> paymentReports = new ArrayList<>();
        for (int i = 1; i <= 60; i++) {
            Double aDouble = paymentReportService.totalAmountOfPayments(paymentReports);
            if (aDouble > totalAmountOfMoneyAfterInterest) {
                double remain = totalAmountOfMoneyAfterInterest - aDouble;
                PaymentReport paymentReport = new PaymentReport();
                paymentReport.setId(i);
                paymentReport.setAmountPerPayment(aDouble);
                paymentReport.setLoanNumber(i);
                paymentReports.add(paymentReport);
                paymentReport.setLoan(loan);
                paymentReportService.saveOrUpdate(paymentReport);
                break;
            }
            if (i % 12 == 0) {
                initialMoney = initialMoney * 2;
            }
            PaymentReport paymentReport = new PaymentReport();
            paymentReport.setId(i);
            paymentReport.setAmountPerPayment((double) initialMoney);
            paymentReport.setLoanNumber(i);
            paymentReports.add(paymentReport);
            paymentReport.setLoan(loan);
            paymentReportService.saveOrUpdate(paymentReport);


        }
    }

    private static void createPaymentBillForEducationalLoan() {
        Student student = (Student) SecurityContext.getCurrentUser();
        Integer totalAmountOfMoneyAfterInterest = totalAmountOfMoneyAfterInterestForEducationalLoan();
        int initialMoney = totalAmountOfMoneyAfterInterest / 372;

        Loan loan = loanService.loan(student, TypeOfLoan.EDUCATIONAL_LOAN);
        List<PaymentReport> paymentReports = new ArrayList<>();
        for (int i = 1; i <= 60; i++) {
            Double aDouble = paymentReportService.totalAmountOfPayments(paymentReports);
            if (aDouble > totalAmountOfMoneyAfterInterest) {
                double remain = totalAmountOfMoneyAfterInterest - aDouble;
                PaymentReport paymentReport = new PaymentReport();
                paymentReport.setId(i);
                paymentReport.setAmountPerPayment(aDouble);
                paymentReport.setLoanNumber(i);
                paymentReports.add(paymentReport);
                paymentReport.setLoan(loan);
                paymentReportService.saveOrUpdate(paymentReport);
                break;
            }
            if (i % 12 == 0) {
                initialMoney = initialMoney * 2;
            }
            PaymentReport paymentReport = new PaymentReport();
            paymentReport.setId(i);
            paymentReport.setAmountPerPayment((double) initialMoney);
            paymentReport.setLoanNumber(i);
            paymentReports.add(paymentReport);
            paymentReport.setLoan(loan);
            paymentReportService.saveOrUpdate(paymentReport);

        }
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
        if (grade.equals(Grade.CONTINUOUS_MASTER) || grade.equals(Grade.DOCTORATE) || grade.equals(Grade.DISCONTINUOUS_SPECIALIZED_DOCTORATE)) {
            graduatedYear = 6;
            return student.getEntranceYear() + graduatedYear;
        }
        return null;
    }


    private static Integer totalAmountOfMoneyAfterInterestForTuitionLoan() {
        Student student = (Student) SecurityContext.getCurrentUser();
        Long singleAmount = loanCategoryService.getAmount(student, TypeOfLoan.STUDENT_TUITION_LOAN);

        if (singleAmount != null) {
            System.out.println("Total money before interest: " + singleAmount);
            double amount = singleAmount * 1.04;
            int result = (int) amount;
            System.out.println("Total money after interest: " + result);
            return result;
        } else {
            return 0;
        }
    }

    private static Integer totalAmountOfMoneyAfterInterestForEducationalLoan() {
        Student student = (Student) SecurityContext.getCurrentUser();
        Long singleAmount = loanCategoryService.getAmount(student, TypeOfLoan.EDUCATIONAL_LOAN);

        if (singleAmount != null) {
            System.out.println("Total money before interest: " + singleAmount);
            double amount = singleAmount * 1.04;
            int result = (int) amount;
            System.out.println("Total money after interest: " + result);
            return result;
        } else {
            return 0;
        }
    }

    private static Integer totalAmountOfMoneyAfterInterestForHousingLoan() {
        Student student = (Student) SecurityContext.getCurrentUser();
        Long singleAmount = loanCategoryService.getAmount(student, TypeOfLoan.HOUSING_LOAN);
        if (singleAmount != null) {
            System.out.println("Total money before interest: " + singleAmount);
            double amount = singleAmount * 1.04;
            int result = (int) amount;
            System.out.println("Total money after interest: " + result);
            return result;
        } else {
            return 0;
        }
    }
}

