package hellodb;

import hellodb.handlers.*;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class App {
    private void registerHandler(JpaHandler jpaHandler) {
        String path = "/" + jpaHandler.getClass().getSimpleName().toLowerCase().replaceAll("handler", "");
        get(path, (req, res) -> {
            res.header("Content-type", "text/plain;charset=utf-8");
            return jpaHandler.handle();
        });
    }

    private void registerDefaultHandlers() {
        registerHandler(new PizzasHandler());
        registerHandler(new ProductsHandler());
        registerHandler(new ReceiptsHandler());
        registerHandler(new StockHandler());
        registerHandler(new OrdersHandler());
        registerHandler(new ClientsHandler());
        registerHandler(new SuppliersHandler());
    }

    private App() {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFiles.location("/public");
        port(8080);

        get("/", (req, res) -> "index.html");
        registerDefaultHandlers();
    }

    public static void main(String[] args) {
        new App();
    }
}
