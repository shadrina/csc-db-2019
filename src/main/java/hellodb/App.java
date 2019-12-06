package hellodb;

import hellodb.handlers.*;
import hellodb.handlers.simple.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import static spark.Spark.*;

public class App {
    private void registerDefaultHandler(DefaultJpaHandler jpaHandler) {
        String path = "/" + jpaHandler.getClass().getSimpleName().toLowerCase().replaceAll("handler", "");
        get(path, (req, res) -> {
            res.header("Content-type", "text/plain;charset=utf-8");
            return jpaHandler.handle();
        });
    }

    private void registerDefaultHandlers() {
        registerDefaultHandler(new PizzasHandler());
        registerDefaultHandler(new ProductsHandler());
        registerDefaultHandler(new ReceiptsHandler());
        registerDefaultHandler(new StockHandler());
        registerDefaultHandler(new OrdersHandler());
        registerDefaultHandler(new ClientsHandler());
        registerDefaultHandler(new SuppliersHandler());
    }

    private App() {
        SalesHandler salesHandler = new SalesHandler();
        UpdateOrderHandler updateOrderHandler = new UpdateOrderHandler();
        UpdateOrderContentsHandler updateOrderContentsHandler = new UpdateOrderContentsHandler();

        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFiles.location("/public");
        port(8080);

        registerDefaultHandlers();
        get("/", (req, res) -> "index.html");
        get("/sales", (req, res) -> {
            String pizza = req.queryParams("pizza");
            return salesHandler.handle(pizza);
        });
        get("/update_order", (req, res) -> {
            Long id = Long.parseLong(req.queryParams("id"));
            String phone = req.queryParams("phone");
            String address = req.queryParams("address");
            Date deliveryTime = new SimpleDateFormat("yyyy-MM-dd HH-mm").parse(req.queryParams("delivery_time"));
            return updateOrderHandler.handle(id, phone, address, deliveryTime);
        });
        get("/update_order_contents", (req, res) -> {
            Long id = Long.parseLong(req.queryParams("id"));
            String pizza = req.queryParams("pizza");
            String count = req.queryParams("count");
            return updateOrderContentsHandler.handle(id, pizza, count);
        });
    }

    public static void main(String[] args) {
        new App();
    }
}
