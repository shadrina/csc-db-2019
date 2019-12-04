package hellodb.entities.ids;

import hellodb.entities.PizzaEntity;
import hellodb.entities.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class ReceiptId implements Serializable {
    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @Getter @Setter
    private PizzaEntity pizza;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @Getter @Setter
    private ProductEntity product;

    @Override
    public String toString() {
        return "Receipt(" + "pizza=" + pizza + ", product=" + product;
    }
}
