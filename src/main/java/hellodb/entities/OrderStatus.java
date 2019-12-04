package hellodb.entities;

import lombok.Getter;
import lombok.Setter;

public enum OrderStatus {
    ACCEPTED("принят"),
    IN_PROGRESS("в обработке"),
    SHIPPED("отгружен"),
    DONE("выполнен");

    @Getter @Setter
    private String description;

    OrderStatus(String description) {
        this.description = description;
    }
}
