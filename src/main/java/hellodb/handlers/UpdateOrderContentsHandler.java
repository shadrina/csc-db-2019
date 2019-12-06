package hellodb.handlers;

import hellodb.entities.OrderInfoEntity;
import hellodb.entities.OrderDetailsEntity;
import hellodb.entities.PizzaEntity;
import hellodb.entities.ids.OrderDetailsId;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class UpdateOrderContentsHandler implements JpaHandler {
    public String handle(Long id, String pizza, String count) {
        List<OrderDetailsEntity> orderDetails = new ArrayList<>();
        run(entityManager -> {
            OrderInfoEntity orderInfoEntity = id == null ? null : entityManager.find(OrderInfoEntity.class, id);
            if (orderInfoEntity == null) {
                throw new RuntimeException("Can`t change order that doesn`t exists");
            }
            TypedQuery<PizzaEntity> queryPizza = entityManager.createQuery(
                    "SELECT P FROM Pizza P WHERE P.name=:pizza",
                    PizzaEntity.class
            );
            queryPizza.setParameter("pizza", pizza);
            List<PizzaEntity> pizzaEntityList = queryPizza.getResultList();
            if (pizzaEntityList.size() == 0) {
                throw new RuntimeException("Can`t change order by add unexisting pizza");
            }
            PizzaEntity pizzaEntity = pizzaEntityList.get(0);

            TypedQuery<OrderDetailsEntity> queryOrderDetails = entityManager.createQuery(
                    "SELECT O FROM OrderDetails O WHERE O.orderDetailsId.orderInfo.id=:order_id AND O.orderDetailsId.pizza.id=:pizza_id",
                    OrderDetailsEntity.class
            );
            queryOrderDetails.setParameter("order_id", id);
            queryOrderDetails.setParameter("pizza_id", pizzaEntity.getId());
            List<OrderDetailsEntity> orderDetailsList = queryOrderDetails.getResultList();
            OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
            if (orderDetailsList.size() == 0) {
                orderDetailsEntity = new OrderDetailsEntity();
                OrderDetailsId orderDetailsId = new OrderDetailsId();
                orderDetailsId.setOrderInfo(orderInfoEntity);
                orderDetailsId.setPizza(pizzaEntity);
                orderDetailsEntity.setOrderDetailsId(orderDetailsId);
                orderDetailsEntity.setAmount(0);
            } else {
                orderDetailsEntity = orderDetailsList.get(0);
            }

            Integer newAmount = Integer.parseInt(count);
            if (count.startsWith("+") || count.startsWith("-")) {
                orderDetailsEntity.setAmount(orderDetailsEntity.getAmount() + newAmount);
            } else {
                orderDetailsEntity.setAmount(newAmount);
            }

            entityManager.persist(orderDetailsEntity);
            
            orderDetails.add(orderDetailsEntity);
        });
        return orderDetails.get(0).toString();
    }
}
