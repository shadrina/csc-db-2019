// Copyright (C) 2019 Dmitry Barashev
package hellodb;

import hellodb.handlers.ClientsHandler;
import hellodb.handlers.PizzasHandler;
import hellodb.handlers.ProductsHandler;
import hellodb.handlers.SuppliersHandler;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

/**
 * Это main класс приложения. Запускает Spark и вешает два обработчика запросов.
 *
 * @author dbms@barashev.net
 */
public class App {
    private final PizzasHandler pizzasHandler;
    private final ProductsHandler productsHandler;
    private final ClientsHandler clientsHandler;
    private final SuppliersHandler suppliersHandler;

    private App(String dbUrl) {
        this.productsHandler = new ProductsHandler(dbUrl);
        this.pizzasHandler = new PizzasHandler(dbUrl);
        this.clientsHandler = new ClientsHandler(dbUrl);
        this.suppliersHandler = new SuppliersHandler(dbUrl);

        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFiles.location("/public");
        port(8080);

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
    }

    public static void main(String[] args) {
        new App("jdbc:postgresql://127.0.0.1:5432/pizzeria?user=postgres&password=postgres");
    }
}
