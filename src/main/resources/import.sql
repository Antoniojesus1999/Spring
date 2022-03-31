insert into regiones(id, nombre) VALUES (1, 'Sudamérica');
insert into regiones(id, nombre) VALUES (2, 'Centroamérica');
insert into regiones(id, nombre) VALUES (3, 'Norteamérica');
insert into regiones(id, nombre) VALUES (4, 'Europa');
insert into regiones(id, nombre) VALUES (5, 'Asia');
insert into regiones(id, nombre) VALUES (6, 'Africa');
insert into regiones(id, nombre) VALUES (7, 'Oceanía');
insert into regiones(id, nombre) VALUES (8, 'Antártida');
insert into clientes (nombre, apellido, email, create_at,region_id) values ('Andrés','Guzman','profesor@gmail.com','2022-01-01',1);
insert into clientes (nombre, apellido, email, create_at,region_id) values ('AntonioJesús','Ponce Vela','ajpvinv@gmail.com','2022-01-01',2);
insert into clientes (nombre, apellido, email, create_at,region_id) values ('Rosario','Vela Garcia','rosario@gmail.com','2022-02-03',3);
insert into clientes (nombre, apellido, email, create_at,region_id) values ('Maryra','Ponce Vela','mayra.mpv@gmail.com','2024-10-11',4);
insert into clientes (nombre, apellido, email, create_at,region_id) values ('José','Ramirez Benitez','jose@gmail.com','2025-04-05',5);
insert into clientes (nombre, apellido, email, create_at,region_id) values ('asdf','Guzman','profesfor@gmail.com','2022-01-01',6);
insert into clientes (nombre, apellido, email, create_at,region_id) values ('fds<','Ponce Vela','ajpvfinv@gmail.com','2022-01-01',7);
insert into clientes (nombre, apellido, email, create_at,region_id) values ('zxcv','Vela Garcia','rosadrio@gmail.com','2022-02-03',8);
insert into clientes (nombre, apellido, email, create_at,region_id) values ('jhgs','Ponce Vela','mayraa.mpv@gmail.com','2024-10-11',1);

/* Creamos algunos usuarios con sus roles */
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('andres','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq',1, 'Andres', 'Guzman','profesor@bolsadeideas.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('admin','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK',1, 'John', 'Doe','jhon.doe@bolsadeideas.com');

INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 1);



/* Populate tabla productos */
INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

/* Creamos algunas facturas */
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());

INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);