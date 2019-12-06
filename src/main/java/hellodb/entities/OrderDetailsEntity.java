package hellodb.entities;

import hellodb.entities.ids.OrderDetailsId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "OrderDetails")
public class OrderDetailsEntity {
    @EmbeddedId
    @Getter @Setter
    OrderDetailsId orderDetailsId;

    @Column(name = "amount")
    @Getter @Setter
    private Integer amount;

    @Override
    public String toString() {
        return orderDetailsId + ", amount=" + amount + ")";
    }
}
