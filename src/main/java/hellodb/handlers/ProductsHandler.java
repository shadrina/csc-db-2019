package hellodb.handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import hellodb.entities.ProductEntity;

public class ProductsHandler {
    private final String dbUrl;

    public ProductsHandler(String dbUrl) {
        this.dbUrl = dbUrl;
    }
  
    public String handle() {
        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            PreparedStatement statement = conn.prepareStatement("SELECT id, name FROM Product");
            List<ProductEntity> result = new ArrayList<>();
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setId(rs.getLong("id"));
                    productEntity.setName(rs.getString("name"));
                    result.add(productEntity);
                }
            }
            return result.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
