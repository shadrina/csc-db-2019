package hellodb.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(name= "phone_number")
    @Getter @Setter
    private String phoneNumber;

    @Column(name = "discount_amount")
    @Getter @Setter
    private Integer discountAmount;

    @Override
    public String toString() {
        return "Client(" + "id=" + id + ", phone_number=" + phoneNumber + ", discount_amount=" + discountAmount + ")";
    }
}
