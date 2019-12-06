package hellodb.handlers;

import hellodb.entities.ClientEntity;
import hellodb.entities.OrderInfoEntity;
import hellodb.entities.OrderStatus;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class UpdateOrderHandler implements JpaHandler {
    public String handle(Long id, String phone, String address, Date deliveryDate) {
        if (!phone.startsWith("8")) {
            throw new RuntimeException("Client phone should begin with '8' digit");
        }
        List<OrderInfoEntity> orderInfo = new ArrayList<>();
        run(entityManager -> {
            OrderInfoEntity orderInfoEntity = id == null ? null : entityManager.find(OrderInfoEntity.class, id);
            if (orderInfoEntity == null) {
                orderInfoEntity = new OrderInfoEntity();
            }
            TypedQuery<ClientEntity> query = entityManager.createQuery(
                    "SELECT C FROM Client C WHERE C.phoneNumber=:phone",
                    ClientEntity.class
            );
            query.setParameter("phone", phone);
            ClientEntity clientEntity = query.getResultList().size() == 0 ? null : query.getSingleResult();
            if (clientEntity == null) {
                clientEntity = new ClientEntity();
                clientEntity.setPhoneNumber(phone);
                clientEntity.setDiscountAmount(0);
                clientEntity.setOrders(new ArrayList<>());
            }
            orderInfoEntity.setClient(clientEntity);
            orderInfoEntity.setAddress(address);
            orderInfoEntity.setStatus(OrderStatus.ACCEPTED);
            orderInfoEntity.setDate(new Date());
            orderInfoEntity.setDeliveryDate(deliveryDate);

            entityManager.persist(clientEntity);
            entityManager.persist(orderInfoEntity);

            orderInfo.add(orderInfoEntity);
        });
        return orderInfo.get(0).toString();
    }
}
