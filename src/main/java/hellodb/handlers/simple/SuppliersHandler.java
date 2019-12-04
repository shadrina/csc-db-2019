package hellodb.handlers.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hellodb.entities.SupplierEntity;

import javax.persistence.TypedQuery;

public class SuppliersHandler implements DefaultJpaHandler {
    @Override
    public String handle() {
        List<SupplierEntity> allSuppliers = new ArrayList<>();
        run(entityManager -> {
            TypedQuery<SupplierEntity> typedQuery = entityManager.createQuery("SELECT S FROM Supplier S", SupplierEntity.class);
            allSuppliers.addAll(typedQuery.getResultList());
        });
        return allSuppliers.stream()
                .map(SupplierEntity::toString)
                .collect(Collectors.joining("\n"));
    }
}
