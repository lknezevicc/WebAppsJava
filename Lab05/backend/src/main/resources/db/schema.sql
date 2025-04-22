CREATE TABLE IF NOT EXISTS restaurants (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    is_opened BOOLEAN NOT NULL DEFAULT TRUE,
    average_delivery_time DECIMAL(5, 2) NOT NULL,
    average_customer_rating DECIMAL(3, 2) NOT NULL,
    max_number_of_orders INT NOT NULL,
    michelin_stars TINYINT NOT NULL
);

CREATE TABLE IF NOT EXISTS working_hours (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    restaurant_id BIGINT NOT NULL,
    day_of_week VARCHAR(20) NOT NULL,
    hours VARCHAR(20) NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);