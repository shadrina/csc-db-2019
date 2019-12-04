package hellodb.entities;

import hellodb.entities.ids.ReceiptId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Receipt")
public class ReceiptEntity {
    @EmbeddedId
    @Getter @Setter
    ReceiptId receiptId;

    @Column(name = "product_amount")
    @Getter @Setter
    private Integer productAmount;

    @Override
    public String toString() {
        return receiptId + ", productAmount=" + productAmount + ")";
    }
}
