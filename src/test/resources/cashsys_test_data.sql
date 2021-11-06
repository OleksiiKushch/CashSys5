USE cashsysdb ;


SET @unit_id = (SELECT id FROM unit WHERE `name` = 'piece');
INSERT INTO product (id, `name`, price, amount, barcode, unit_id) VALUES
    (DEFAULT, 'stapler LUXON', 2.5, 70, '4613244322657', @unit_id),
    (DEFAULT, 'comb ParallaX', 5, 23, '4605821233212', @unit_id),
    (DEFAULT, 'pencil LUXON', 0.8, 271, '4611366728476', @unit_id),
    (DEFAULT, 'Pepsi Cola 1l', 1, 110, '4634271223504', @unit_id),
    (DEFAULT, 'Coca Cola 1.5l', 1.4, 64, '3204992254639', @unit_id),
    (DEFAULT, 'pen JoJ', 3.8, 189, '0601762649669', @unit_id);

SET @unit_id = (SELECT id FROM unit WHERE `name` = 'kilogram');
INSERT INTO product (id, `name`, price, amount, barcode, unit_id) VALUES
    (DEFAULT, 'ordinary sugar', 1.1, 500.5, '4657488712948', @unit_id);

SET @unit_id = (SELECT id FROM unit WHERE `name` = 'litre');
INSERT INTO product (id, `name`, price, amount, barcode, unit_id) VALUES
    (DEFAULT, 'Fanta Jingle', 1.1, 140.8, '4411164778032', @unit_id);


SET @role_id = (SELECT id FROM user_role WHERE `name` = 'cashier');
INSERT INTO `user` (id, email, first_name, middle_name, last_name, passhash, role_id) VALUES
    (DEFAULT, 'tammy.reuben@cashsys.com', 'Tammy', 'Donald', 'Reuben', '79B180E5FF6A23229A5FCE280C0D14C84832FE019D0AD5CDA140C1BFD6B19112', @role_id),
    (DEFAULT, 'bob.yang@cashsys.com', 'Bob', 'Ken', 'Yang', '4E672DBA718E2552FC20E5ECFD4A0EFB47609F50EC10CB21DCA75C0800D124D5', @role_id),
    (DEFAULT, 'alex.fpster@cashsys.com', 'Alex', 'Henry', 'Fpster', 'DB0BDF2FC0893C0848CC11795E952735A2E18D38F008E39C568961B477BD2CB7', @role_id),
    (DEFAULT, 'maria.miller@cashsys.com', 'Maria', 'Loiuse', 'Miller', '3F94E34DD40435CCF08E34AA158F6630ED7F11017C781F83A9BB5FF2129BBDE7', @role_id),
    (DEFAULT, 'jennifer.white@cashsys.com', 'Jennifer', 'Mie', 'White', 'AB72FD85278EBD7EAB739ABD3A9BCD02A901B186AAD57EDB82CBA3E6BE98DCC4', @role_id);

SET @role_id = (SELECT id FROM user_role WHERE `name` = 'senior cashier');
INSERT INTO `user` (id, email, first_name, middle_name, last_name, passhash, role_id) VALUES
    (DEFAULT, 'tom.lee@cashsys.com', 'Tom', 'Ado', 'Lee', 'DA0AED7FEDE29351692B86BADC77859B4716C7195E37571D531B9108686917C4', @role_id);

SET @role_id = (SELECT id FROM user_role WHERE `name` = 'commodity expert');
INSERT INTO `user` (id, email, first_name, middle_name, last_name, passhash, role_id) VALUES
    (DEFAULT, 'jeri.wood@cashsys.com', 'Jeri', 'T', 'Wood', '34EBA500E76F442BC2DB6A5C04CDD2979D11D5848BF982D8E3DFF25EF2777A26', @role_id);


SET @status_id = (SELECT id FROM receipt_status WHERE `name` = 'normal');
SET @payment_id = (SELECT id FROM payment WHERE `name` = 'cash');
INSERT INTO receipt (id, date_time, `change`, payment_id, user_id, status_id) VALUES
    (DEFAULT, now(), 18.2, @payment_id, 1, @status_id),
    (DEFAULT, now(), 12, @payment_id, 1, @status_id),
    (DEFAULT, now(), 0, @payment_id, 2, @status_id),
    (DEFAULT, now(), 2.5, @payment_id, 1, @status_id);

SET @payment_id = (SELECT id FROM payment WHERE `name` = 'electronic');
INSERT INTO receipt (id, date_time, `change`, payment_id, user_id, status_id) VALUES
    (DEFAULT, now(), 0, @payment_id, 2, @status_id),
    (DEFAULT, now(), 0, @payment_id, 1, @status_id),
    (DEFAULT, now(), 0, @payment_id, 2, @status_id),
    (DEFAULT, now(), 0, @payment_id, 4, @status_id);

SET @status_id = (SELECT id FROM receipt_status WHERE `name` = 'rejected');
SET @payment_id = (SELECT id FROM payment WHERE `name` = 'electronic');
INSERT INTO receipt (id, date_time, `change`, payment_id, user_id, status_id) VALUES
    (DEFAULT, now(), 0, @payment_id, 6, @status_id);

SET @payment_id = (SELECT id FROM payment WHERE `name` = 'cash');
INSERT INTO receipt (id, date_time, `change`, payment_id, user_id, status_id) VALUES
    (DEFAULT, now(), 0.9, @payment_id, 6, @status_id),
    (DEFAULT, now(), 5, @payment_id, 6, @status_id);


INSERT INTO receipt_has_product (receipt_id, product_id, price, amount) VALUES
    (1, 3, 0.8, 2), (1, 4, 1, 1), (1, 8, 1.2, 1.4),
    (2, 1, 2.5, 1),
    (3, 4, 1, 2), (3, 7, 1.1, 1),
    (4, 5, 1.3, 3), (4, 2, 5.1, 1),
    (5, 1, 2.5, 2), (5, 7, 1.1, 0.5), (5, 8, 1.2, 2.9), (5, 3, 0.8, 2),
    (6, 4, 1, 1), (6, 2, 5, 2),
    (7, 5, 1.2, 1),
    (8, 7, 0.9, 30),
    (9, 8, 1.1, 5), (9, 1, 2.5, 1), (9, 3, 0.8, 2),
    (10, 4, 1, 1), (10, 3, 0.8, 3),
    (11, 1, 2.3, 1), (11, 2, 4.9, 1), (11, 5, 1.3, 2);