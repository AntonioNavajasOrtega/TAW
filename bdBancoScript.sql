CREATE TABLE estado_cuenta(
    id INT PRIMARY KEY,    
    tipo VARCHAR(15)
);

CREATE TABLE tipo_transaccion(
    id INT PRIMARY KEY,    
    tipo VARCHAR(15)
);

CREATE TABLE tipo_solicitud(
    id INT PRIMARY KEY,    
    tipo VARCHAR(15)
);

CREATE TABLE tipo_empleado(
    id INT PRIMARY KEY,    
    tipo VARCHAR(15)
);

CREATE TABLE tipo_cliente(
    id INT PRIMARY KEY,    
    tipo VARCHAR(15)
);
INSERT INTO tipo_cliente (id, tipo) VALUES (1, 'Propietario'), (2,'Socio'), (3,'Autorizado');
INSERT INTO estado_cuenta (id,tipo) VALUES (1, 'Activa'), (2,'Bloqueada');
INSERT INTO tipo_transaccion (id,tipo) VALUES (1, 'Cambio'), (2,'Pago');
INSERT INTO tipo_solicitud (id,tipo) VALUES (1, 'Alta'), (2,'Activacion');
INSERT INTO tipo_empleado (id,tipo) VALUES (1, 'Gestor'), (2,'Asistente');

CREATE TABLE empresa(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre varchar(50) NOT NULL,
    direccion varchar(100) NOT NULL,
    telefono varchar(20) NOT NULL
);
CREATE TABLE cliente (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    direccion VARCHAR(100),
    telefono VARCHAR(12),
    nif VARCHAR(9) NOT NULL,
    email VARCHAR(50) NOT NULL,
	contrasena VARCHAR(50) NOT NULL,
    empresa_id INT,
	FOREIGN KEY (empresa_id) REFERENCES empresa(id),
    PRIMARY KEY (id)
);

CREATE TABLE empleado(
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre_usuario VARCHAR(50) NOT NULL,
  contrasena VARCHAR(100) NOT NULL,
  tipo INT NOT NULL,
  FOREIGN KEY (tipo) REFERENCES tipo_empleado(id)
);

CREATE TABLE cuenta (
    id INT NOT NULL AUTO_INCREMENT,
    iban VARCHAR(34) NOT NULL,
    saldo DECIMAL(10, 2) NOT NULL,
	estado INT NOT NULL,
	swift VARCHAR(11) NOT NULL,
    pais VARCHAR(50) NOT NULL,
    cliente_id INT NOT NULL,
	empleado_id INT NOT NULL,
	empresa_id INT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (estado) REFERENCES estado_cuenta(id),
	FOREIGN KEY (empresa_id) REFERENCES empresa(id),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    FOREIGN KEY (empleado_id) REFERENCES empleado(id)
);

CREATE TABLE transaccion (
    id INT NOT NULL AUTO_INCREMENT,
    tipo INT NOT NULL,
    cantidad DECIMAL(10, 2) NOT NULL,
    fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    moneda VARCHAR(10),
    cuenta_origen_id INT,
    cuenta_destino_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (tipo) REFERENCES tipo_transaccion(id),
    FOREIGN KEY (cuenta_origen_id) REFERENCES cuenta(id),
    FOREIGN KEY (cuenta_destino_id) REFERENCES cuenta(id)
);

CREATE TABLE TipoClienteRelacionado(
	cliente_id INT NOT NULL,
    cuenta_id INT NOT NULL,    
	tipo INT NOT NULL,
    bloqueado BOOLEAN NOT NULL,
	FOREIGN KEY (tipo) REFERENCES tipo_cliente(id),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    FOREIGN KEY (cuenta_id) REFERENCES cuenta(id),
    PRIMARY KEY (cliente_id, cuenta_id)
);


CREATE TABLE solicitud (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tipo INT NOT NULL,
  fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  empleado_id INT NOT NULL,
  cuenta_id INT NOT NULL,
  cliente_id INT NOT NULL,
  empresa_id INT,
  FOREIGN KEY (tipo) REFERENCES tipo_solicitud(id),
  FOREIGN KEY (empleado_id) REFERENCES empleado(id),
  FOREIGN KEY (cuenta_id) REFERENCES cuenta(id),
FOREIGN KEY (empresa_id) REFERENCES empresa(id),
  FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE conversacion(
	id INT AUTO_INCREMENT PRIMARY KEY,
	abierta boolean NOT NULL,
	empleado INT NOT NULL,
    cliente INT NOT NULL,
    asunto VARCHAR(50),
	fecha_apertura TIMESTAMP,
	fecha_cierre TIMESTAMP,
	FOREIGN KEY (empleado) REFERENCES empleado(id),
	FOREIGN KEY (cliente) REFERENCES cliente(id)
);


CREATE TABLE mensaje(
	id INT AUTO_INCREMENT PRIMARY KEY,
	fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	contenido VARCHAR(400) NOT NULL,
    conversacion INT NOT NULL,   
	emisor_cliente INT,
    emisor_empleado INT,
    receptor_cliente INT,
    receptor_empleado INT,
    FOREIGN KEY (emisor_cliente) REFERENCES cliente(id),
    FOREIGN KEY (emisor_empleado) REFERENCES empleado(id),
    FOREIGN KEY (receptor_cliente) REFERENCES cliente(id),
    FOREIGN KEY (receptor_empleado) REFERENCES empleado(id),
    FOREIGN KEY (conversacion) REFERENCES conversacion(id)
);

insert into empleado (nombre_usuario, contrasena, tipo) values ('gestorUsuario', 'gestorPassword', 1);
insert into empleado (nombre_usuario, contrasena, tipo) values ('asistenteUsuario', 'asistentePassword', 2);
insert into cliente (nombre,apellido,direccion,telefono,nif,email,contrasena,empresa_id) values ('juanjo', 'torres', 'calle calañas, malaga', 657786925, '77429357A','jjtp@uma.es', '123abc', null);
insert into cuenta (iban, saldo, estado, swift, pais, cliente_id, empleado_id, empresa_id) values ('3242423434', 1000.32, 1, '342', 'ESPAÑA', 1,1,null);
insert into cliente (nombre,apellido,direccion,telefono,nif,email,contrasena,empresa_id) values ('Almudena', 'arrabal', 'calle Cervantes, malaga', 656738117, '77429377J','almu@gmail.com', '12345', null);
insert into cuenta (iban, saldo, estado, swift, pais, cliente_id, empleado_id, empresa_id) values ('54663554636', 1000.32, 1, '342', 'ESPAÑA', 2,1,null);

insert into empresa (nombre, direccion, telefono) values ('Microsoft', 'Calle Falsa 123', '123456789');
insert into cliente (nombre,apellido,direccion,telefono,nif,email,contrasena,empresa_id) values ('antonio', 'navajas', 'calle rodeo, malaga', 1234892736, '27445357A','email@uma.es', '123456', 1);
insert into cliente (nombre,apellido,direccion,telefono,nif,email,contrasena,empresa_id) values ('jose', 'heredero', 'calle zapato, malaga', 952380922, '13329358B','china@uma.es', 'abcdef', 1);
insert into cuenta (iban, saldo, estado, swift, pais, cliente_id, empleado_id, empresa_id) values ('7282683489', 2500, 1, '342', 'ESPAÑA', 3,1,1);
insert into TipoClienteRelacionado (cliente_id, cuenta_id, tipo, bloqueado) values (3, 3, 2, 0);
insert into TipoClienteRelacionado (cliente_id, cuenta_id, tipo, bloqueado) values (4, 3, 3, 0);





