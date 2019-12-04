package hellodb.handlers;

import hellodb.entities.PizzaEntity;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PizzasHandler implements JpaHandler {
    public String handle() {
        List<PizzaEntity> allPizzas = new ArrayList<>();
        run(entityManager -> {
            TypedQuery<PizzaEntity> typedQuery = entityManager.createQuery("SELECT P FROM Pizza P", PizzaEntity.class);
            allPizzas.addAll(typedQuery.getResultList());
        });
        return allPizzas.stream()
                .map(PizzaEntity::toString)
                .collect(Collectors.joining("\n"));
    }
}
