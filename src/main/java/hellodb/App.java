// Copyright (C) 2019 Dmitry Barashev
package hellodb;

import hellodb.handlers.PizzasHandler;

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

    private App(String dbUrl) {
        this.pizzasHandler = new PizzasHandler(dbUrl);
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFiles.location("/public");
        port(8080);

        get("/hello", (req, res) -> {
            return "Hello DB";
        });
        get("/pizzas", (req, res) -> {
            res.header("Content-type", "text/plain;charset=utf-8");
            return pizzasHandler.handle();
        });
    }

    public static void main(String[] args) {
        new App("jdbc:postgresql://127.0.0.1:5432/pizzeria?user=postgres&password=postgres");
    }
}
