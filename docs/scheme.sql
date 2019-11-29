CREATE TABLE Pizza(
    id SERIAL PRIMARY KEY,
    name TEXT UNIQUE NOT NULL,
    weight INT NOT NULL CHECK(weight > 0),
    cost INT NOT NULL CHECK(cost >= 0)
);

CREATE TABLE Product(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE Stock(
    product_id INT NOT NULL UNIQUE REFERENCES Product,
    remaining INT NOT NULL CHECK(remaining >= 0)
);

CREATE TABLE Receipt(
    pizza_id INT REFERENCES Pizza,
    product_id INT REFERENCES Product,
    product_amount INT NOT NULL CHECK(product_amount > 0),
    UNIQUE(pizza_id, product_id)
);

CREATE TABLE Client(
    id INT PRIMARY KEY,
    phone_number TEXT UNIQUE NOT NULL,
    discount_amount INT NOT NULL CHECK(discount_amount >= 0 AND discount_amount <= 100) DEFAULT 0
);

CREATE TYPE OrderStatus AS ENUM ('принят', 'в обработке', 'отгружен', 'выполнен');

CREATE TABLE OrderInfo(
    id INT PRIMARY KEY,
    client_id INT REFERENCES Client,
    address TEXT NOT NULL,
    status OrderStatus NOT NULL,
    date DATE NOT NULL,
    delivery_date DATE NOT NULL
);

CREATE TABLE OrderDetails(
    order_id INT REFERENCES OrderInfo,
    pizza_id INT REFERENCES Pizza,
    amount INT NOT NULL CHECK (amount >= 0),
    UNIQUE(order_id, pizza_id)
);

CREATE TABLE Supplier(
    id INT PRIMARY KEY,
    name TEXT UNIQUE NOT NULL,
    phone_number TEXT UNIQUE
);

CREATE TABLE SupplierArrangement(
    supplier_id INT REFERENCES Supplier,
    product_id INT REFERENCES Product,
    cost INT NOT NULL CHECK(cost >= 0),
    UNIQUE(supplier_id, product_id)
);