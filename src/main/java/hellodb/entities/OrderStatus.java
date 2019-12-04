package hellodb.entities;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.nio.charset.StandardCharsets;

@AllArgsConstructor
public enum OrderStatus {
    ACCEPTED("принят"),
    IN_PROGRESS("в обработке"),
    SHIPPED("отгружен"),
    DONE("выполнен");

    @Setter
    private String description;

    public String getDescription() {
        return new String(description.getBytes(), StandardCharsets.UTF_8);
    }
}
