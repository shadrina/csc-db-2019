package hellodb.handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import hellodb.entities.SupplierEntity;

public class SuppliersHandler {
    private final String dbUrl;

    public SuppliersHandler(String dbUrl) {
        this.dbUrl = dbUrl;
    }
  
    public String handle() {
        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            PreparedStatement statement = conn.prepareStatement("SELECT id, name, phone_number FROM Supplier");
            List<SupplierEntity> result = new ArrayList<>();
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    SupplierEntity supllierEntity = new SupplierEntity();
                    supllierEntity.setId(rs.getLong("id"));
                    supllierEntity.setName(rs.getString("name"));
                    supllierEntity.setPhoneNumber(rs.getString("phone_number"));
                    result.add(supllierEntity);
                }
            }
            return result.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
