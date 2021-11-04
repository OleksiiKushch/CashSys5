
INSERT INTO `user_role` (id, name) VALUES (1, 'cashier'),(2, 'senior cashier'),(3, 'commodity expert');

INSERT INTO `payment` (id, name) VALUES (1, 'cash'),(2, 'card');

INSERT INTO `unit` (id, name) VALUES (1, 'piece'),(2, 'kilogram'),(3, 'litre');

INSERT INTO `product` (id, name, price, amount, unit_id, barcode) VALUES
(1, 'stapler LUXON', 2.5, 70, 1, 1111111111111),
(2, 'comb ParallaX', 5, 23, 1, 1111111111112),
(3, 'ordinary sugar', 1.1, 500.0, 2, 1111111111113),
(4, 'pencil LUXON', 0.8, 271, 1, 1111111111114),
(5, 'Pepsi Cola', 2.5, 110, 1, 1111111111115),
(6, 'Coca Cola', 2.5, 134.2, 3, 1111111111116),
(7, 'pen JoJ', 2.5, 189, 1, 1111111111117);