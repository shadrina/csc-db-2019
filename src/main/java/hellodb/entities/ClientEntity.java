package hellodb.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column
    @Getter @Setter
    private String phoneNumber;

    @Column
    @Getter @Setter
    private Integer discountAmount;

    @Override
    public String toString() {
        return "Client(" + "id=" + id + ", phone_number=" + phoneNumber + ", discount_amount=" + discountAmount + ")";
    }
}
