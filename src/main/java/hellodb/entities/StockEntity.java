package hellodb.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Stock")
public class StockEntity implements Serializable {
    @Id
    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @Getter @Setter
    private ProductEntity product;

    @Column
    @Getter @Setter
    private Integer remaining;

    @Override
    public String toString() {
        return "Stock(" + "product=" + product + ", remaining=" + remaining + ")";
    }
}
