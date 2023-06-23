INSERT INTO tb_user (id, first_name, last_name, email, password, role) VALUES (1, 'Alex', 'Brown', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 'ROLE_USER');
INSERT INTO tb_user (id, first_name, last_name, email, password, role) VALUES (2, 'Bob', 'Grey', 'bob@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 'ROLE_MANAGER');
INSERT INTO tb_user (id, first_name, last_name, email, password, role) VALUES (3, 'Maria', 'Green', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 'ROLE_ADMIN');

INSERT INTO tb_printer (id, name) VALUES (1, 'Impressora 1');
INSERT INTO tb_printer (id, name) VALUES (2, 'Impressora 2');
INSERT INTO tb_printer (id, name) VALUES (3, 'Impressora 3');

INSERT INTO tb_print_request (id, name, file, user_id, created_at, scheduled_date, description) VALUES (1, 'Print Request 1', NULL, 1, CURRENT_TIMESTAMP, CURRENT_DATE, 'Description 1');
INSERT INTO tb_print_request (id, name, file, user_id, created_at, scheduled_date, description) VALUES (2, 'Print Request 2', NULL, 1, CURRENT_TIMESTAMP, CURRENT_DATE, 'Description 2');
INSERT INTO tb_print_request (id, name, file, user_id, created_at, scheduled_date, description) VALUES (3, 'Print Request 3', NULL, 1, CURRENT_TIMESTAMP, CURRENT_DATE, 'Description 3');
INSERT INTO tb_print_request (id, name, file, user_id, created_at, scheduled_date, description) VALUES (4, 'Print Request 4', NULL, 2, CURRENT_TIMESTAMP, CURRENT_DATE, 'Description 4');
INSERT INTO tb_print_request (id, name, file, user_id, created_at, scheduled_date, description) VALUES (5, 'Print Request 5', NULL, 2, CURRENT_TIMESTAMP, CURRENT_DATE, 'Description 5');
INSERT INTO tb_print_request (id, name, file, user_id, created_at, scheduled_date, description) VALUES (6, 'Print Request 6', NULL, 2, CURRENT_TIMESTAMP, CURRENT_DATE, 'Description 6');
INSERT INTO tb_print_request (id, name, file, user_id, created_at, scheduled_date, description) VALUES (7, 'Print Request 7', NULL, 2, CURRENT_TIMESTAMP, CURRENT_DATE, 'Description 7');
INSERT INTO tb_print_request (id, name, file, user_id, created_at, scheduled_date, description) VALUES (8, 'Print Request 8', NULL, 3, CURRENT_TIMESTAMP, CURRENT_DATE, 'Description 8');
INSERT INTO tb_print_request (id, name, file, user_id, created_at, scheduled_date, description) VALUES (9, 'Print Request 9', NULL, 3, CURRENT_TIMESTAMP, CURRENT_DATE, 'Description 9');
INSERT INTO tb_print_request (id, name, file, user_id, created_at, scheduled_date, description) VALUES (10, 'Print Request 10', NULL, 3, CURRENT_TIMESTAMP, CURRENT_DATE, 'Description 10');
INSERT INTO tb_print_request (id, name, file, user_id, created_at, scheduled_date, description) VALUES (11, 'Print Request 11', NULL, 3, CURRENT_TIMESTAMP, CURRENT_DATE, 'Description 11');
INSERT INTO tb_print_request (id, name, file, user_id, created_at, scheduled_date, description) VALUES (12, 'Print Request 12', NULL, 3, CURRENT_TIMESTAMP, CURRENT_DATE, 'Description 12');
INSERT INTO tb_print_request (id, name, file, user_id, created_at, scheduled_date, description) VALUES (13, 'Print Request 13', NULL, 3, CURRENT_TIMESTAMP, CURRENT_DATE, 'Description 13');

INSERT INTO tb_print_job (printer_id, print_request_id, status) VALUES (1, 1, 'WAITING');
INSERT INTO tb_print_job (printer_id, print_request_id, status) VALUES (2, 2, 'APPROVED');
INSERT INTO tb_print_job (printer_id, print_request_id, status) VALUES (3, 3, 'WAITING');
INSERT INTO tb_print_job (printer_id, print_request_id, status) VALUES (1, 4, 'IN_PROGRESS');
INSERT INTO tb_print_job (printer_id, print_request_id, status) VALUES (2, 5, 'COMPLETED');
INSERT INTO tb_print_job (printer_id, print_request_id, status) VALUES (3, 6, 'FAILED');
INSERT INTO tb_print_job (printer_id, print_request_id, status) VALUES (1, 7, 'CANCELED');
INSERT INTO tb_print_job (printer_id, print_request_id, status) VALUES (3, 8, 'WAITING');
INSERT INTO tb_print_job (printer_id, print_request_id, status) VALUES (3, 9, 'WAITING');
INSERT INTO tb_print_job (printer_id, print_request_id, status) VALUES (3, 10, 'WAITING');
INSERT INTO tb_print_job (printer_id, print_request_id, status) VALUES (3, 11, 'IN_PROGRESS');
INSERT INTO tb_print_job (printer_id, print_request_id, status) VALUES (NULL, 12, 'PENDING');
INSERT INTO tb_print_job (printer_id, print_request_id, status) VALUES (NULL, 13, 'PENDING');

INSERT INTO tb_product (name, description, quantity) VALUES ('Filamento ABS', 'Filamento ABS Premium para impressora 3D 500g 1,7mm (Branco Gesso)', 1);
INSERT INTO tb_product (name, description, quantity) VALUES ('Filamento PLA', 'Filamento PLA Premium para Impressora 3D 1,75mm 1kg(Preto)', 10);
INSERT INTO tb_product (name, description, quantity) VALUES ('Placa MDF', 'MDF cru 3mm (verde)', 5);
