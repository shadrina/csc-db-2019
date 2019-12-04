package hellodb.entities;

import hellodb.entities.converters.OrderStatusConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "OrderInfo")
public class OrderInfoEntity {
    @Id
    @SequenceGenerator(name = "orderinfo_id_seq", sequenceName = "orderinfo_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderinfo_id_seq")
    @Column(name = "id")
    @Getter @Setter
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @MapsId
    @Getter @Setter
    private ClientEntity client;

    @Column
    @Getter @Setter
    private String address;

    @Column
    @Convert(converter = OrderStatusConverter.class)
    @Getter @Setter
    private OrderStatus status;

    @Column
    @Getter @Setter
    private Date date;

    @Column(name = "delivery_date")
    @Getter @Setter
    private Date deliveryDate;

    @Override
    public String toString() {
        return "OrderInfo(" + "id=" + id + ", client=" + client + ", address=" + address + ", status=" + status +
               ", date=" + date + ", delivery_date=" + deliveryDate + ")";
    }
}
