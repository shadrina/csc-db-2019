package hellodb.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Client")
public class ClientEntity {
    @Id
    @SequenceGenerator(name = "client_id_seq", sequenceName = "client_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_seq")
    @Column
    @Getter @Setter
    private Long id;

    @Column(name= "phone_number")
    @Getter @Setter
    private String phoneNumber;

    @Column(name = "discount_amount")
    @Getter @Setter
    private Integer discountAmount;

    @OneToMany(mappedBy = "client")
    @Getter @Setter
    private List<OrderInfoEntity> orders;

    @Override
    public String toString() {
        return "Client(" + "id=" + id + ", phone_number=" + phoneNumber + ", discount_amount=" + discountAmount + ")";
    }
}
