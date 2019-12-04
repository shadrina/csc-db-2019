CREATE TABLE Pizza(
    id SERIAL NOT NULL PRIMARY KEY,
    name TEXT UNIQUE NOT NULL,
    weight INT NOT NULL CHECK(weight > 0),
    cost INT NOT NULL CHECK(cost >= 0)
);

CREATE TABLE Product(
    id SERIAL NOT NULL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE Stock(
    product_id INT NOT NULL PRIMARY KEY REFERENCES Product,
    remaining INT NOT NULL CHECK(remaining >= 0)
);

CREATE TABLE Receipt(
    pizza_id INT NOT NULL REFERENCES Pizza,
    product_id INT NOT NULL REFERENCES Product,
    product_amount INT NOT NULL CHECK(product_amount > 0),
    PRIMARY KEY (pizza_id, product_id)
);

CREATE TABLE Client(
    id SERIAL NOT NULL PRIMARY KEY,
    phone_number TEXT UNIQUE NOT NULL,
    discount_amount INT NOT NULL CHECK(discount_amount >= 0 AND discount_amount <= 100) DEFAULT 0
);

CREATE TYPE OrderStatus AS ENUM ('ACCEPTED', 'IN_PROGRESS', 'SHIPPED', 'DONE');

CREATE TABLE OrderInfo(
    id SERIAL NOT NULL PRIMARY KEY,
    client_id INT REFERENCES Client,
    address TEXT NOT NULL,
    status OrderStatus NOT NULL,
    date DATE NOT NULL,
    delivery_date DATE NOT NULL
);

CREATE TABLE OrderDetails(
    order_id INT NOT NULL REFERENCES OrderInfo,
    pizza_id INT NOT NULL REFERENCES Pizza,
    amount INT NOT NULL CHECK (amount >= 0),
    PRIMARY KEY (order_id, pizza_id)
);

CREATE TABLE Supplier(
    id SERIAL NOT NULL PRIMARY KEY,
    name TEXT UNIQUE NOT NULL,
    phone_number TEXT UNIQUE
);

CREATE TABLE SupplierArrangement(
    supplier_id INT NOT NULL REFERENCES Supplier,
    product_id INT NOT NULL REFERENCES Product,
    cost INT NOT NULL CHECK(cost >= 0),
    PRIMARY KEY (supplier_id, product_id)
);

INSERT INTO Product VALUES (1, 'Sausage');
INSERT INTO Product VALUES (2, 'Cheese');
INSERT INTO Product VALUES (3, 'Bread');
INSERT INTO Product VALUES (4, 'Salt');
INSERT INTO Product VALUES (5, 'Sugar');
INSERT INTO Product VALUES (6, 'Salad');
INSERT INTO Product VALUES (7, 'Tomato');
INSERT INTO Product VALUES (8, 'Cucumber');
INSERT INTO Product VALUES (9, 'Ketchup');
INSERT INTO Product VALUES (10, 'Sour cream');
INSERT INTO Product VALUES (11, 'Bacon');
INSERT INTO Product VALUES (12, 'Eggs');
INSERT INTO Product VALUES (13, 'Milk');
INSERT INTO Product VALUES (14, 'Onion');
INSERT INTO Product VALUES (15, 'Beef');
INSERT INTO Product VALUES (16, 'Pineapple');

INSERT INTO Pizza VALUES (1, 'Margherita', 520, 450);
INSERT INTO Pizza VALUES (2, 'Pepperoni', 500, 420);
INSERT INTO Pizza VALUES (3, 'Bonanza', 430, 480);
INSERT INTO Pizza VALUES (4, 'Alfredo', 550, 500);
INSERT INTO Pizza VALUES (5, 'Hawaiian', 444, 400);

INSERT INTO Stock VALUES (1, 1000);
INSERT INTO Stock VALUES (2, 1000);
INSERT INTO Stock VALUES (3, 1000);
INSERT INTO Stock VALUES (4, 1000);
INSERT INTO Stock VALUES (5, 1000);
INSERT INTO Stock VALUES (6, 1000);
INSERT INTO Stock VALUES (7, 1000);
INSERT INTO Stock VALUES (8, 1000);
INSERT INTO Stock VALUES (9, 1000);
INSERT INTO Stock VALUES (10, 1000);
INSERT INTO Stock VALUES (11, 1000);
INSERT INTO Stock VALUES (12, 1000);
INSERT INTO Stock VALUES (13, 1000);
INSERT INTO Stock VALUES (14, 1000);
INSERT INTO Stock VALUES (15, 1000);
INSERT INTO Stock VALUES (16, 1000);

INSERT INTO Receipt VALUES (1, 1, 100);
INSERT INTO Receipt VALUES (1, 2, 100);
INSERT INTO Receipt VALUES (1, 3, 100);
INSERT INTO Receipt VALUES (1, 6, 100);
INSERT INTO Receipt VALUES (1, 7, 100);
INSERT INTO Receipt VALUES (1, 8, 100);
INSERT INTO Receipt VALUES (1, 10, 100);
INSERT INTO Receipt VALUES (1, 12, 100);
INSERT INTO Receipt VALUES (1, 13, 100);
INSERT INTO Receipt VALUES (1, 14, 100);
INSERT INTO Receipt VALUES (2, 1, 100);
INSERT INTO Receipt VALUES (2, 2, 100);
INSERT INTO Receipt VALUES (2, 4, 100);
INSERT INTO Receipt VALUES (2, 6, 100);
INSERT INTO Receipt VALUES (2, 8, 100);
INSERT INTO Receipt VALUES (2, 9, 100);
INSERT INTO Receipt VALUES (2, 11, 100);
INSERT INTO Receipt VALUES (2, 12, 100);
INSERT INTO Receipt VALUES (2, 13, 100);
INSERT INTO Receipt VALUES (2, 14, 100);
INSERT INTO Receipt VALUES (2, 15, 100);
INSERT INTO Receipt VALUES (3, 2, 100);
INSERT INTO Receipt VALUES (3, 3, 100);
INSERT INTO Receipt VALUES (3, 5, 100);
INSERT INTO Receipt VALUES (3, 7, 100);
INSERT INTO Receipt VALUES (3, 8, 100);
INSERT INTO Receipt VALUES (3, 11, 100);
INSERT INTO Receipt VALUES (3, 12, 100);
INSERT INTO Receipt VALUES (3, 13, 100);
INSERT INTO Receipt VALUES (3, 14, 100);
INSERT INTO Receipt VALUES (3, 15, 100);
INSERT INTO Receipt VALUES (4, 2, 100);
INSERT INTO Receipt VALUES (4, 3, 100);
INSERT INTO Receipt VALUES (4, 5, 100);
INSERT INTO Receipt VALUES (4, 7, 100);
INSERT INTO Receipt VALUES (4, 8, 100);
INSERT INTO Receipt VALUES (4, 10, 100);
INSERT INTO Receipt VALUES (4, 13, 100);
INSERT INTO Receipt VALUES (4, 14, 100);
INSERT INTO Receipt VALUES (5, 1, 100);
INSERT INTO Receipt VALUES (5, 2, 100);
INSERT INTO Receipt VALUES (5, 4, 100);
INSERT INTO Receipt VALUES (5, 6, 100);
INSERT INTO Receipt VALUES (5, 8, 100);
INSERT INTO Receipt VALUES (5, 10, 100);
INSERT INTO Receipt VALUES (5, 13, 100);
INSERT INTO Receipt VALUES (5, 16, 100);

INSERT INTO Client VALUES (1, '89131231324', 0);
INSERT INTO Client VALUES (2, '89531333365', 10);
INSERT INTO Client VALUES (3, '89265642376', 10);
INSERT INTO Client VALUES (4, '89523345656', 15);
INSERT INTO Client VALUES (5, '89138051365', 5);

INSERT INTO OrderInfo VALUES (1, 1, '8649 Aspen Ave. Pataskala, OH 43062', 'DONE', '2019-12-01 12:05:00', '2019-12-02 13:00:00');
INSERT INTO OrderInfo VALUES (2, 2, '910 Snake Hill Circle Chambersburg, PA 17201', 'SHIPPED', '2019-12-03 11:00:00', '2019-12-03 16:00:00');
INSERT INTO OrderInfo VALUES (3, 4, '708 Brandywine St. Clinton Township, MI 48035', 'ACCEPTED', '2019-12-03 12:00:00', '2019-12-03 14:00:00');

INSERT INTO OrderDetails VALUES (1, 1, 2);
INSERT INTO OrderDetails VALUES (2, 3, 1);
INSERT INTO OrderDetails VALUES (2, 5, 1);
INSERT INTO OrderDetails VALUES (3, 2, 2);
INSERT INTO OrderDetails VALUES (3, 4, 4);

INSERT INTO Supplier VALUES (1, 'Bacon&Beef', '3333232');
INSERT INTO Supplier VALUES (2, 'Greatest Products', '3340907');
INSERT INTO Supplier VALUES (3, 'Pyaterochka', '5555505');

INSERT INTO SupplierArrangement VALUES (1, 11, 73);
INSERT INTO SupplierArrangement VALUES (1, 15, 63);
INSERT INTO SupplierArrangement VALUES (2, 1, 34);
INSERT INTO SupplierArrangement VALUES (2, 2, 44);
INSERT INTO SupplierArrangement VALUES (2, 3, 11);
INSERT INTO SupplierArrangement VALUES (2, 4, 23);
INSERT INTO SupplierArrangement VALUES (2, 5, 54);
INSERT INTO SupplierArrangement VALUES (2, 6, 33);
INSERT INTO SupplierArrangement VALUES (2, 7, 27);
INSERT INTO SupplierArrangement VALUES (2, 8, 14);
INSERT INTO SupplierArrangement VALUES (3, 1, 24);
INSERT INTO SupplierArrangement VALUES (3, 2, 34);
INSERT INTO SupplierArrangement VALUES (3, 3, 41);
INSERT INTO SupplierArrangement VALUES (3, 4, 13);
INSERT INTO SupplierArrangement VALUES (3, 5, 45);
INSERT INTO SupplierArrangement VALUES (3, 6, 28);
INSERT INTO SupplierArrangement VALUES (3, 7, 25);
INSERT INTO SupplierArrangement VALUES (3, 8, 13);
INSERT INTO SupplierArrangement VALUES (3, 9, 47);
INSERT INTO SupplierArrangement VALUES (3, 10, 12);
INSERT INTO SupplierArrangement VALUES (3, 12, 44);
INSERT INTO SupplierArrangement VALUES (3, 13, 25);
INSERT INTO SupplierArrangement VALUES (3, 14, 23);
INSERT INTO SupplierArrangement VALUES (3, 16, 14);