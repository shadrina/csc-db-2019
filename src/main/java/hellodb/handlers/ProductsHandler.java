package hellodb.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hellodb.entities.ProductEntity;

import javax.persistence.TypedQuery;

public class ProductsHandler implements JpaHandler {
    public String handle() {
        List<ProductEntity> allProducts = new ArrayList<>();
        run(entityManager -> {
            TypedQuery<ProductEntity> typedQuery = entityManager.createQuery("SELECT P FROM Product P", ProductEntity.class);
            allProducts.addAll(typedQuery.getResultList());
        });
        return allProducts.stream()
                .map(ProductEntity::toString)
                .collect(Collectors.joining("\n"));
    }
}
