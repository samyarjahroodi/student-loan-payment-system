import menu.MainMenu;
import utility.EntityManagerProvider;

import javax.persistence.EntityManager;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        MainMenu.signUpMenu();
    }
}
