package hellodb.handlers.simple;

import hellodb.entities.ReceiptEntity;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptsHandler implements DefaultJpaHandler {
    @Override
    public String handle() {
        List<ReceiptEntity> allReceipts = new ArrayList<>();
        run(entityManager -> {
            TypedQuery<ReceiptEntity> typedQuery = entityManager.createQuery("SELECT R FROM Receipt R", ReceiptEntity.class);
            allReceipts.addAll(typedQuery.getResultList());
        });
        return allReceipts.stream()
                .map(ReceiptEntity::toString)
                .collect(Collectors.joining("\n"));
    }
}

