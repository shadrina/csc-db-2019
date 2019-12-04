package hellodb.handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import hellodb.entities.ClientEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClientsHandler {
    private final String dbUrl;
  
    public String handle() {
        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            PreparedStatement statement = conn.prepareStatement("SELECT id, phone_number, discount_amount FROM Client");
            List<ClientEntity> result = new ArrayList<>();
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    ClientEntity clientEntity = new ClientEntity();
                    clientEntity.setId(rs.getLong("id"));
                    clientEntity.setPhoneNumber(rs.getString("phone_number"));
                    clientEntity.setDiscountAmount(rs.getInt("discount_amount"));
                    result.add(clientEntity);
                }
            }
            return result.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
