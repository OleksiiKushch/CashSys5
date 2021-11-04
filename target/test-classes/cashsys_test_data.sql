USE cashsysdb ;

SET @id = (SELECT id FROM unit WHERE `name` = 'piece');
INSERT INTO product (id, `name`, price, amount, barcode, unit_id) VALUES
    (DEFAULT, 'stapler LUXON', 2.5, 70, '1111111111111', @id),
    (DEFAULT, 'comb ParallaX', 5, 23, '1111111111112', @id),
    (DEFAULT, 'pencil LUXON', 0.8, 271, '1111111111113', @id),
    (DEFAULT, 'Pepsi Cola 1l', 1, 110, '1111111111114', @id),
    (DEFAULT, 'Coca Cola 1.5l', 1.4, 64, '1111111111115', @id),
    (DEFAULT, 'pen JoJ', 3.8, 189, '1111111111116', @id);

SET @id = (SELECT id FROM unit WHERE `name` = 'kilogram');
INSERT INTO product (id, `name`, price, amount, barcode, unit_id) VALUES
    (DEFAULT, 'ordinary sugar', 1.1, 500.5, '1111111111117', @id);

SET @id = (SELECT id FROM unit WHERE `name` = 'litre');
INSERT INTO product (id, `name`, price, amount, barcode, unit_id) VALUES
    (DEFAULT, 'Fanta Jingle', 1.1, 140.8, '1111111111118', @id);