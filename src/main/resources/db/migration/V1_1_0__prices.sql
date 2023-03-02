CREATE TABLE IF NOT EXISTS price (

    price_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    start_date timestamp,
    end_date timestamp,
    price_list int,
    product_id int,
    priority int,
    price dec,
    currency varchar(20)

)