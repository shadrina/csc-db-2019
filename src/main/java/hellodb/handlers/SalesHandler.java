package hellodb.handlers;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SalesHandler implements JpaHandler {
    public String handle(String pizza) {
        List<Object[]> result = new ArrayList<>();
        run(entityManager -> {
            Query query = entityManager.createNativeQuery(
                    "WITH orders_with_details AS (\n" +
                            "  SELECT *\n" +
                            "  FROM OrderDetails OD \n" +
                            "  JOIN OrderInfo OI ON OD.order_id=OI.id\n" +
                            "  JOIN Pizza P ON OD.pizza_id=P.id\n" +
                            "  ORDER BY OI.id, P.name\n" +
                            "), totals AS (\n" +
                            "  SELECT \n" +
                            "    P.name AS name, \n" +
                            "    P.cost AS cost,\n" +
                            "    P.weight AS weight,\n" +
                            "    SUM(OWD.amount) OVER (PARTITION BY P.name) AS total_amount, \n" +
                            "    P.cost * SUM(OWD.amount) OVER (PARTITION BY P.name) AS total_cost\n" +
                            "  FROM orders_with_details OWD\n" +
                            "  JOIN Pizza P ON OWD.pizza_id=P.id\n" +
                            "), totals_with_rank AS (\n" +
                            "  SELECT \n" +
                            "    totals.name, \n" +
                            "    totals.cost, \n" +
                            "    totals.total_amount, \n" +
                            "    totals.total_cost,\n" +
                            "    totals.weight,\n" +
                            "    rank() OVER (ORDER BY totals.total_cost DESC) AS rank\n" +
                            "  FROM totals\n" +
                            "  GROUP BY totals.name, totals.cost, totals.total_amount, totals.total_cost, totals.weight\n" +
                            "), pizza_sales_per_day AS (\n" +
                            "  SELECT \n" +
                            "    P.name AS name, \n" +
                            "    EXTRACT(DOW FROM OI.delivery_date) AS day_of_week, \n" +
                            "    COUNT(*), \n" +
                            "    ROW_NUMBER() OVER (PARTITION BY P.name ORDER BY COUNT(*) DESC) AS row\n" +
                            "  FROM OrderInfo OI \n" +
                            "  JOIN OrderDetails OD ON OI.id=OD.order_id\n" +
                            "  JOIN Pizza P on OD.pizza_id=P.id\n" +
                            "  GROUP BY P.name, EXTRACT(DOW FROM OI.delivery_date)\n" +
                            "  ORDER BY COUNT(*) DESC\n" +
                            "), best_sales_day_info AS (\n" +
                            "  SELECT\n" +
                            "    pizza_sales_per_day.name, \n" +
                            "    pizza_sales_per_day.day_of_week, \n" +
                            "    pizza_sales_per_day.count\n" +
                            "  FROM (\n" +
                            "    SELECT \n" +
                            "      P.name AS name, \n" +
                            "      EXTRACT(DOW FROM OI.delivery_date) AS day_of_week, \n" +
                            "      COUNT(*), \n" +
                            "      ROW_NUMBER() OVER (PARTITION BY P.name ORDER BY COUNT(*) DESC) AS row\n" +
                            "    FROM OrderInfo OI\n" +
                            "    JOIN OrderDetails OD ON OI.id=OD.order_id\n" +
                            "    JOIN Pizza P on OD.pizza_id=P.id\n" +
                            "    GROUP BY P.name, EXTRACT(DOW FROM OI.delivery_date)\n" +
                            "    ORDER BY COUNT(*) DESC\n" +
                            "  ) AS pizza_sales_per_day\n" +
                            "  WHERE pizza_sales_per_day.row <= 1\n" +
                            ")\n" +
                            "---------------------------------------------------------------------------\n" +
                            "SELECT \n" +
                            "  totals_with_rank.name, \n" +
                            "  totals_with_rank.cost, \n" +
                            "  totals_with_rank.total_amount, \n" +
                            "  totals_with_rank.rank, \n" +
                            "  CAST (best_sales_day_info.day_of_week AS INT) \n" +
                            "FROM totals_with_rank\n" +
                            "JOIN best_sales_day_info ON totals_with_rank.name = best_sales_day_info.name\n" +
                            "ORDER BY totals_with_rank.rank;");
            result.addAll(query.getResultList());
        });
        return (pizza != null ? result.stream().filter(item -> item[0].equals(pizza)) : result.stream())
                .map(Arrays::toString)
                .collect(Collectors.joining("\n"));
    }
}
