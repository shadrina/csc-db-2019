package hellodb.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "OrderInfo")
public class OrderInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @MapsId
    @Getter @Setter
    private ClientEntity client;

    @Column
    @Getter @Setter
    private String address;

    @Column
    @Enumerated(EnumType.STRING)
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
