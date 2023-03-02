CREATE TABLE IF NOT EXISTS price (

    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    brand_id int,
    start_date varchar(20),
    end_date varchar(20),
    price_list int,
    product_id int,
    priority int,
    price numeric(20,2),
    currency varchar(20)

);

INSERT INTO price (id, brand_id, start_date, end_date, price_list, product_id, priority, price, currency) VALUES
    (1, 1, '2020-06-14T00:00:00', '2020-12-31T23:59:59', 1, 35455, 0, 35.50, 'EUR'),
    (2, 1, '2020-06-14T15:00:00', '2020-06-14T18:30:00', 1, 35455, 1, 25.45, 'EUR'),
    (3, 1, '2020-06-15T00:00:00', '2020-06-15T11:00:00', 1, 35455, 1, 30.50, 'EUR'),
    (4, 1, '2020-06-15T16:00:00', '2020-12-31T23:59:59', 1, 35455, 1, 38.95, 'EUR');