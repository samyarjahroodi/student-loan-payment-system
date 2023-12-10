package utility;

import repository.impl.*;
import service.impl.*;

import javax.persistence.EntityManager;

@SuppressWarnings("unused")
public class ApplicationContext {

    private static final EntityManager ENTITY_MANAGER;
    private static final CardRepositoryImpl CARD_REPOSITORY;
    private static final LoanCategoryRepositoryImpl LOAN_CATEGORY_REPOSITORY;
    private static final LoanRepositoryImpl LOAN_REPOSITORY;
    private static final PaymentReportRepositoryImpl PAYMENT_REPORT_REPOSITORY;
    private static final StudentRepositoryImpl STUDENT_REPOSITORY;
    private static final StudentSpouseRepositoryImpl STUDENT_SPOUSE_REPOSITORY;
    private static final UniversityRepositoryImpl UNIVERSITY_REPOSITORY;


    private static final CardServiceImpl CARD_SERVICE;
    private static final LoanCategoryServiceImpl LOAN_CATEGORY_SERVICE;
    private static final LoanServiceImpl LOAN_SERVICE;
    private static final PaymentReportServiceImpl PAYMENT_REPORT_SERVICE;
    private static final StudentServiceImpl STUDENT_SERVICE;
    private static final StudentSpouseServiceImpl STUDENT_SPOUSE_SERVICE;
    private static final UniversityServiceImpl UNIVERSITY_SERVICE;


    static {
        ENTITY_MANAGER = EntityManagerProvider.getEntityManager();
        CARD_REPOSITORY = new CardRepositoryImpl(ENTITY_MANAGER);
        LOAN_CATEGORY_REPOSITORY = new LoanCategoryRepositoryImpl(ENTITY_MANAGER);
        LOAN_REPOSITORY = new LoanRepositoryImpl(ENTITY_MANAGER);
        PAYMENT_REPORT_REPOSITORY = new PaymentReportRepositoryImpl(ENTITY_MANAGER);
        STUDENT_REPOSITORY = new StudentRepositoryImpl(ENTITY_MANAGER);
        STUDENT_SPOUSE_REPOSITORY = new StudentSpouseRepositoryImpl(ENTITY_MANAGER);
        UNIVERSITY_REPOSITORY = new UniversityRepositoryImpl(ENTITY_MANAGER);

        CARD_SERVICE = new CardServiceImpl(CARD_REPOSITORY);
        LOAN_CATEGORY_SERVICE = new LoanCategoryServiceImpl(LOAN_CATEGORY_REPOSITORY);
        LOAN_SERVICE = new LoanServiceImpl(LOAN_REPOSITORY);
        PAYMENT_REPORT_SERVICE = new PaymentReportServiceImpl(PAYMENT_REPORT_REPOSITORY);
        STUDENT_SERVICE = new StudentServiceImpl(STUDENT_REPOSITORY);
        STUDENT_SPOUSE_SERVICE = new StudentSpouseServiceImpl(STUDENT_SPOUSE_REPOSITORY);
        UNIVERSITY_SERVICE = new UniversityServiceImpl(UNIVERSITY_REPOSITORY);

    }


    public CardServiceImpl getCARD_SERVICE() {
        return CARD_SERVICE;
    }

    public LoanCategoryServiceImpl getLOAN_CATEGORY_SERVICE() {
        return LOAN_CATEGORY_SERVICE;
    }

    public LoanServiceImpl getLOAN_SERVICE() {
        return LOAN_SERVICE;
    }

    public PaymentReportServiceImpl getPAYMENT_REPORT_SERVICE() {
        return PAYMENT_REPORT_SERVICE;
    }


    public StudentServiceImpl getSTUDENT_SERVICE() {
        return STUDENT_SERVICE;
    }

    public StudentSpouseServiceImpl getSTUDENT_SPOUSE_SERVICE() {
        return STUDENT_SPOUSE_SERVICE;
    }

    public UniversityServiceImpl getUNIVERSITY_SERVICE() {
        return UNIVERSITY_SERVICE;
    }
}
