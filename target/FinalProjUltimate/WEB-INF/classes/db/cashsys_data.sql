USE cashsysdb ;

INSERT INTO user_role (id, `name`)
VALUES
    (1, 'cashier'),
    (2, 'senior cashier'),
    (3, 'commodity expert');

INSERT INTO payment (id, `name`)
VALUES
    (1, 'cash'),
    (2, 'electronic');

INSERT INTO unit (id, `name`)
VALUES
    (1, 'piece'),
    (2, 'kilogram'),
    (3, 'litre');

INSERT INTO receipt_status (id, `name`)
VALUES
    (1, 'normal'),
    (2, 'rejected');