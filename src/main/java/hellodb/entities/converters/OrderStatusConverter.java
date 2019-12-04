package hellodb.entities.converters;

import hellodb.entities.OrderStatus;

import javax.persistence.AttributeConverter;

public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {
    @Override
    public String convertToDatabaseColumn(OrderStatus orderStatus) {
        if (orderStatus == null) {
            return null;
        }
        return orderStatus.getDescription();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String status) {
        if (OrderStatus.ACCEPTED.getDescription().equals(status)) {
            return OrderStatus.ACCEPTED;
        } else if (OrderStatus.IN_PROGRESS.getDescription().equals(status)) {
            return OrderStatus.IN_PROGRESS;
        } else if (OrderStatus.SHIPPED.getDescription().equals(status)) {
            return OrderStatus.SHIPPED;
        } else if (OrderStatus.DONE.getDescription().equals(status)) {
            return OrderStatus.DONE;
        } else {
            throw new RuntimeException("Unknown order status");
        }
    }
}
