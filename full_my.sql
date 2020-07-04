BEGIN;

DROP TABLE IF EXISTS goods CASCADE;
CREATE TABLE goods (id bigserial PRIMARY KEY, name varchar(255), price numeric(6, 2));
INSERT INTO goods (name, price) VALUES ('phone', 45.45),
('pc', 243.11),
('sofa', 334.55),
('auto', 211.55),
('tablet', 236.66),
('case', 346.46),
('mac', 346.45);

DROP TABLE IF EXISTS customer CASCADE;
CREATE TABLE customer (id bigserial PRIMARY KEY, name varchar(255));
INSERT INTO customer (name) VALUES ('Ivanov'),
('Frolov'),
('Petrov');

DROP TABLE IF EXISTS goods_customer CASCADE;
CREATE TABLE goods_customer (goods_id bigint, customer_id bigint, FOREIGN KEY (goods_id) REFERENCES goods (id), FOREIGN KEY (customer_id) REFERENCES customer (id));
INSERT INTO goods_customer (goods_id, customer_id) VALUES
(1, 1),
(2, 3),
(3, 1),
(4, 3),
(5, 1),
(6, 1),
(7, 3),
(2, 2),
(4, 2),
(5, 3),
(7, 2),
(1, 2);

COMMIT;