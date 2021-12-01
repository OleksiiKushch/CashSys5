USE cashsysdbtest ;

INSERT INTO user_role (id, `name`)
VALUES
    (DEFAULT, 'cashier'),
    (DEFAULT, 'senior cashier'),
    (DEFAULT, 'commodity expert');

INSERT INTO payment (id, `name`)
VALUES
    (DEFAULT, 'cash'),
    (DEFAULT, 'electronic');

INSERT INTO unit (id, `name`)
VALUES
    (DEFAULT, 'piece'),
    (DEFAULT, 'kilogram'),
    (DEFAULT, 'litre');

INSERT INTO receipt_status (id, `name`)
VALUES
    (DEFAULT, 'normal'),
    (DEFAULT, 'rejected');