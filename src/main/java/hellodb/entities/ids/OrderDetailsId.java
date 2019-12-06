package hellodb.entities.ids;

import hellodb.entities.OrderInfoEntity;
import hellodb.entities.PizzaEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class OrderDetailsId implements Serializable {
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    @MapsId
    @Getter @Setter
    private OrderInfoEntity orderInfo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pizza_id")
    @MapsId
    @Getter @Setter
    private PizzaEntity pizza;

    @Override
    public String toString() {
        return "OrderDetails(" + "order_info=" + orderInfo + ", pizza=" + pizza;
    }
}
