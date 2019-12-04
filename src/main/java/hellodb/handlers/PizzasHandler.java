package hellodb.handlers;

import hellodb.entities.PizzaEntity;
import lombok.AllArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PizzasHandler {
    private final String dbUrl;

    public String handle() {
        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            PreparedStatement statement = conn.prepareStatement("SELECT id, name, weight, cost FROM Pizza");
            List<PizzaEntity> result = new ArrayList<>();
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    PizzaEntity pizzaEntity = new PizzaEntity();
                    pizzaEntity.setId(rs.getLong("id"));
                    pizzaEntity.setName(rs.getString("name"));
                    pizzaEntity.setWeight(rs.getInt("weight"));
                    pizzaEntity.setCost(rs.getInt("cost"));
                    result.add(pizzaEntity);
                }
            }
            return result.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
