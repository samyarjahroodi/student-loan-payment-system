package utility;

import entity.Person;
import lombok.Getter;

import java.time.LocalDate;

@SuppressWarnings("unused")
public class SecurityContext {
    @Getter
    private static Person currentUser;

    public static void fillContext(Person baseUser) {
        currentUser = baseUser;
    }


    public static Integer getCurrentUserId() {
        return currentUser.getId();
    }

    @Getter
    private static LocalDate todayDate;

    public static void fillContext(LocalDate today) {
        todayDate = today;
    }
}
