package hellodb.handlers.simple;

import hellodb.entities.OrderInfoEntity;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersHandler implements DefaultJpaHandler {
    @Override
    public String handle() {
        List<OrderInfoEntity> orders = new ArrayList<>();
        run(entityManager -> {
            TypedQuery<OrderInfoEntity> typedQuery = entityManager.createQuery("SELECT OI FROM OrderInfo OI", OrderInfoEntity.class);
            orders.addAll(typedQuery.getResultList());
        });
        return orders.stream()
                .map(OrderInfoEntity::toString)
                .collect(Collectors.joining("\n"));
    }
}
