package hellodb.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "supplier")
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column
    @Getter @Setter
    private String name;

    @Column
    @Getter @Setter
    private String phoneNumber;

    @Override
    public String toString() {
        return "Supplier(" + "id=" + id + ", name=" + name + ", phone_number=" + phoneNumber + ")";
    }
}
