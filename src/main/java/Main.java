import utility.EntityManagerProvider;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
    }
}
