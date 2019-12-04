package hellodb.handlers;

import hellodb.entities.StockEntity;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StockHandler implements JpaHandler {
    @Override
    public String handle() {
        List<StockEntity> stock = new ArrayList<>();
        run(entityManager -> {
            TypedQuery<StockEntity> typedQuery = entityManager.createQuery("SELECT S FROM Stock S", StockEntity.class);
            stock.addAll(typedQuery.getResultList());
        });
        return stock.stream()
                .map(StockEntity::toString)
                .collect(Collectors.joining("\n"));
    }
}
