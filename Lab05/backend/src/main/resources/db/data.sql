INSERT INTO restaurants (name, address, phone_number, email, description, is_opened, average_delivery_time, average_customer_rating, max_number_of_orders, michelin_stars) VALUES
    ('Pizza House', 'Zagrebačka 10', '099111222', 'pizza@house.com', 'Pizza and more', TRUE, 30.0, 4.5, 50, 2),
    ('Burger King', 'Ilica 23', '099333444', 'burger@king.com', 'Fast food with a twist', FALSE, 25.0, 4.2, 40, 1),
    ('Sushi Bar', 'Savska 50', '099555666', 'sushi@bar.com', 'Fresh and tasty sushi', TRUE, 40.0, 4.8, 30, 3),
    ('BBQ Joint', 'Vukovarska 15', '099777888', 'bbq@joint.com', 'BBQ delights', TRUE, 35.0, 4.6, 45, 2),
    ('Healthy Bites', 'Gajeva 3', '099999000', 'healthy@bites.com', 'Nutritious meals', FALSE, 20.0, 4.9, 25, 1);

-- Pizza House
INSERT INTO working_hours (restaurant_id, day_of_week, hours) VALUES
    (1, 'MONDAY', '10:00 - 23:00'),
    (1, 'TUESDAY', '10:00 - 23:00'),
    (1, 'WEDNESDAY', '10:00 - 23:00'),
    (1, 'THURSDAY', '10:00 - 23:00'),
    (1, 'FRIDAY', '10:00 - 00:00'),
    (1, 'SATURDAY', '12:00 - 00:00'),
    (1, 'SUNDAY', '12:00 - 22:00');

-- Burger King
INSERT INTO working_hours (restaurant_id, day_of_week, hours) VALUES
    (2, 'MONDAY', '09:00 - 22:00'),
    (2, 'TUESDAY', '09:00 - 22:00'),
    (2, 'WEDNESDAY', '09:00 - 22:00'),
    (2, 'THURSDAY', '09:00 - 22:00'),
    (2, 'FRIDAY', '09:00 - 23:00'),
    (2, 'SATURDAY', '11:00 - 23:00'),
    (2, 'SUNDAY', '11:00 - 21:00');

-- Sushi Bar
INSERT INTO working_hours (restaurant_id, day_of_week, hours) VALUES
    (3, 'MONDAY', '12:00 - 22:00'),
    (3, 'TUESDAY', '12:00 - 22:00'),
    (3, 'WEDNESDAY', '12:00 - 22:00'),
    (3, 'THURSDAY', '12:00 - 22:00'),
    (3, 'FRIDAY', '12:00 - 23:00'),
    (3, 'SATURDAY', '13:00 - 23:00'),
    (3, 'SUNDAY', '13:00 - 20:00');

-- BBQ Joint
INSERT INTO working_hours (restaurant_id, day_of_week, hours) VALUES
    (4, 'MONDAY', '11:00 - 22:00'),
    (4, 'TUESDAY', '11:00 - 22:00'),
    (4, 'WEDNESDAY', '11:00 - 22:00'),
    (4, 'THURSDAY', '11:00 - 22:00'),
    (4, 'FRIDAY', '11:00 - 23:00'),
    (4, 'SATURDAY', '12:00 - 23:00'),
    (4, 'SUNDAY', '12:00 - 21:00');

-- Healthy Bites
INSERT INTO working_hours (restaurant_id, day_of_week, hours) VALUES
    (5, 'MONDAY', '08:00 - 20:00'),
    (5, 'TUESDAY', '08:00 - 20:00'),
    (5, 'WEDNESDAY', '08:00 - 20:00'),
    (5, 'THURSDAY', '08:00 - 20:00'),
    (5, 'FRIDAY', '08:00 - 21:00'),
    (5, 'SATURDAY', '10:00 - 21:00'),
    (5, 'SUNDAY', '10:00 - 19:00');