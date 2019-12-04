package hellodb;

import hellodb.handlers.*;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class App {
    private final PizzasHandler pizzasHandler = new PizzasHandler();
    private final ProductsHandler productsHandler = new ProductsHandler();
    private final ClientsHandler clientsHandler = new ClientsHandler();
    private final SuppliersHandler suppliersHandler = new SuppliersHandler();
    private final ReceiptsHandler receiptsHandler = new ReceiptsHandler();
    private final StockHandler stockHandler = new StockHandler();

    private App() {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFiles.location("/public");
        port(8080);

        get("/", (req, res) -> "index.html");
        get("/pizzas", (req, res) -> {
            res.header("Content-type", "text/plain;charset=utf-8");
            return pizzasHandler.handle();
        });
        get("/products", (req, res) -> {
            res.header("Content-type", "text/plain;charset=utf-8");
            return productsHandler.handle();
        });
        get("/clients", (req, res) -> {
            res.header("Content-type", "text/plain;charset=utf-8");
            return clientsHandler.handle();
        });
        get("/suppliers", (req, res) -> {
            res.header("Content-type", "text/plain;charset=utf-8");
            return suppliersHandler.handle();
        });
        get("/receipts", (req, res) -> {
            res.header("Content-type", "text/plain;charset=utf-8");
            return receiptsHandler.handle();
        });
        get("/stock", (req, res) -> {
            res.header("Content-type", "text/plain;charset=utf-8");
            return stockHandler.handle();
        });
    }

    public static void main(String[] args) {
        new App();
    }
}
