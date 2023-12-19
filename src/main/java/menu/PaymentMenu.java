package menu;

import entity.loan.Loan;
import entity.loan.PaymentReport;
import entity.student.Grade;
import entity.student.Student;
import service.impl.LoanCategoryServiceImpl;
import service.impl.LoanServiceImpl;
import service.impl.PaymentReportServiceImpl;
import utility.ApplicationContext;
import utility.SecurityContext;

import java.text.ParseException;
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
        String string = """
                1-paid installments
                2-Unpaid installments
                3-pay installments
                4-show number
                5-create bills             
                """;
        System.out.println(string);
        switch (scanner.nextInt()) {
            case 1 -> paidInstallments();
            case 2 -> unpaidInstallments();
            case 3 -> payInstallments();
            case 4 -> totalAmountOfMoneyAfterInterest();
            case 5 -> createPaymentBill();
            default -> System.out.println("Invalid input");
        }
    }

    private static void payInstallments() throws ParseException {
        Boolean studentIsGraduated = LoanMenu.checkIfStudentIsGraduated();

    }

    private static void unpaidInstallments() {

    }

    private static void paidInstallments() {

    }

    private static List<Integer> creatDate() {
        Integer integer = whenStudentIsGraduated();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            numbers.add(integer + i);
        }
        return numbers;
    }

    private static void createPaymentBill() {
        Student student = (Student) SecurityContext.getCurrentUser();
        Integer totalAmountOfMoneyAfterInterest = totalAmountOfMoneyAfterInterest();
        int initialMoney = totalAmountOfMoneyAfterInterest / 372;

        List<Loan> loans = student.getLoans();
        List<PaymentReport> paymentReports = new ArrayList<>();
        for (int i = 1; i <= 60; i++) {
            Double aDouble = paymentReportService.totalAmountOfPayments(paymentReports);
            if (aDouble > totalAmountOfMoneyAfterInterest) {
                double remain = totalAmountOfMoneyAfterInterest - aDouble;
                PaymentReport paymentReport = new PaymentReport();
                paymentReport.setId(i);
                paymentReport.setAmountPerPayment( aDouble);
                paymentReport.setLoanNumber(i);
                paymentReports.add(paymentReport);
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
            paymentReportService.saveOrUpdate(paymentReport);

//            for (Loan loan : loans) {
//                loan.getPaymentReport().add(paymentReport);
//                loanService.saveOrUpdate(loan);
//            }
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


    private static Integer totalAmountOfMoneyAfterInterest() {
        Student student = (Student) SecurityContext.getCurrentUser();
        List<Long> singleAmount = loanCategoryService.getAmount(student);

        if (singleAmount != null && !singleAmount.isEmpty()) {
            System.out.println("Total money before interest: " + singleAmount);
            double amount = singleAmount.get(0) * 1.04;
            int result = (int) amount;
            System.out.println("Total money after interest: " + result);
            return result;
        } else {
            return 0;
        }
    }
}

