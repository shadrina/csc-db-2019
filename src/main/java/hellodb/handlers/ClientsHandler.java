package hellodb.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hellodb.entities.ClientEntity;

import javax.persistence.TypedQuery;

public class ClientsHandler implements JpaHandler {
    public String handle() {
        List<ClientEntity> allClients = new ArrayList<>();
        run(entityManager -> {
            TypedQuery<ClientEntity> typedQuery = entityManager.createQuery("SELECT C FROM Client C", ClientEntity.class);
            allClients.addAll(typedQuery.getResultList());
        });
        return allClients.stream()
                .map(ClientEntity::toString)
                .collect(Collectors.joining("\n"));
    }
}
