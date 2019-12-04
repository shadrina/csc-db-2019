package hellodb.handlers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.function.Consumer;

interface JpaHandler {
    default void run(Consumer<EntityManager> code) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Postgres");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            code.accept(entityManager);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
