package menu;


import entity.loan.Loan;
import entity.loan.LoanCategory;
import entity.loan.PaymentType;
import entity.loan.TypeOfLoan;
import entity.student.Grade;
import service.impl.LoanCategoryServiceImpl;
import service.impl.LoanServiceImpl;
import utility.ApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static menu.SignUpMenu.student;

@SuppressWarnings("unused")
public class LoanMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LoanServiceImpl loanService = ApplicationContext.getLOAN_SERVICE();
    private static final LoanCategoryServiceImpl loanCategoryService = ApplicationContext.getLOAN_CATEGORY_SERVICE();


    public static void primaryMenuForLoanMenu() throws ParseException {
        String string = """
                1-tuition loan
                2-education loan
                3-housing loan
                3-login menu
                4-exit
                """;
        System.out.println(string);
        switch (scanner.nextInt()) {
            case 1 -> tuitionLoan();
            case 2 -> educationLoan();
            case 3 -> housingLoan();
            case 4 -> LogInMenu.loginMenu();
            case 5 -> System.exit(0);
            default -> System.out.println("Invalid input!");
        }
    }

    public static void tuitionLoan() throws ParseException {
        Loan loan = new Loan();
        LoanCategory loanCategory = new LoanCategory();
        loanCategory.setTypeOfLoan(TypeOfLoan.STUDENT_TUITION_LOAN);
        loanCategory.setPaymentType(PaymentType.ONE_EACH_TERM);
        String string = """
                You can get loan from 1TH of ABAN
                          to
                      7TH of ABAN
                          or
                from 25TH of BAHMAN
                          to
                      2TH of ESFAND
                ---your pattern should match "\"yyyy-MM-dd\""---               
                """;
        System.out.println(string);
        String date = scanner.next();
        isValidDateInOrderToGetLoan(date);
        Grade grade = student.getGrade();
        if (grade.equals(Grade.ASSOCIATE) || grade.equals(Grade.CONTINUOUS_BACHELOR) ||
                grade.equals(Grade.DISCONTINUOUS_BACHELOR)) {
            loanCategory.setAmount(1900000L);
            loan.setLoanCategory(loanCategory);
            loan.setStudent(student);
            loanService.saveOrUpdate(loan);
            loanCategoryService.saveOrUpdate(loanCategory);
        } else if (grade.equals(Grade.CONTINUOUS_MASTER)
                || grade.equals(Grade.DISCONTINUOUS_MASTER)
                || grade.equals(Grade.DOCTORATE) || grade.equals(Grade.CONTINUOUS_DOCTORATE)) {
            loanCategory.setAmount(2250000L);
            loanCategoryService.saveOrUpdate(loanCategory);
            loan.setLoanCategory(loanCategory);
            loan.setStudent(student);
            loanService.saveOrUpdate(loan);
        } else if (grade.equals(Grade.DISCONTINUOUS_SPECIALIZED_DOCTORATE)) {
            loanCategory.setAmount(2600000L);
            loanCategoryService.saveOrUpdate(loanCategory);
            loan.setLoanCategory(loanCategory);
            loan.setStudent(student);
            loanService.saveOrUpdate(loan);
        }
    }

    private static void educationLoan() {

    }

    private static void housingLoan() {


    }


    public static void isValidDateInOrderToGetLoan(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date userDate = dateFormat.parse(date);
        Date date2 = dateFormat.parse("1402-08-01");
        Date date3 = dateFormat.parse("1402-08-07");
        Date date4 = dateFormat.parse("1402-10-25");
        Date date5 = dateFormat.parse("1402-11-02");

        if (userDate.compareTo(date2) >= 0 && userDate.compareTo(date3) <= 0 ||
                userDate.compareTo(date4) >= 0 && userDate.compareTo(date5) <= 0) {
            System.out.println("Loan can be obtained");
        } else {
            throw new IllegalArgumentException("Loan can only be obtained between " +
                    "1402-08-01 and 1402-08-07 or 1402-10-25 and 1402-11-02");
        }
    }
}
