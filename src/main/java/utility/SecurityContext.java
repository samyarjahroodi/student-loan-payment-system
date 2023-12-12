package utility;

import entity.Person;
import lombok.Getter;

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
}
