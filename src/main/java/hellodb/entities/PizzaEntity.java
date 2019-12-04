package hellodb.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Pizza")
public class PizzaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column
    @Getter @Setter
    private String name;

    @Column
    @Getter @Setter
    private Integer weight;

    @Column
    @Getter @Setter
    private Integer cost;

    @Override
    public String toString() {
        return "Pizza(" + "id=" + id + ", name=" + name + ", weight=" + weight + ", cost=" + cost + ")";
    }
}
