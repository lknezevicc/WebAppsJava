CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_groups (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_user_group (
    user_id BIGINT NOT NULL,
    user_group_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, user_group_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (user_group_id) REFERENCES user_groups(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS restaurants (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    opened BOOLEAN NOT NULL DEFAULT TRUE,
    average_delivery_time DECIMAL(5, 2) NOT NULL,
    average_customer_rating DECIMAL(3, 2) NOT NULL,
    max_number_of_orders INT NOT NULL,
    michelin_stars TINYINT NOT NULL
);

CREATE TABLE IF NOT EXISTS working_hours (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    restaurant_id BIGINT,
    day_of_week VARCHAR(20) NOT NULL,
    working_hour VARCHAR(20) NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS reviews (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    rating TINYINT NOT NULL,
    restaurant_id BIGINT,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS refresh_tokens (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    token VARCHAR(255) NOT NULL,
    expiry_date TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);