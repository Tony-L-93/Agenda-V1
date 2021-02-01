CREATE DATABASE `agenda_oscar_antonio`;
USE agenda_oscar_antonio;

CREATE TABLE `direccion`
(
  `idDireccion` int(10) AUTO_INCREMENT,
  `calle` varchar(100) NOT NULL,
  `altura` int(8) NOT NULL,
  `piso` varchar(10),
  `dpto` varchar(10),
  PRIMARY KEY (`idDireccion`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `localidad`
(
  `id` int(10) AUTO_INCREMENT,
  `localidad` varchar(45) NOT NULL,
  `provincia` varchar(45) NOT NULL,
  `pais` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE unique_localidad(localidad,provincia,pais)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `localidad`
(
  `id` int(10) AUTO_INCREMENT,
  `localidad` varchar(45) NOT NULL,
  `provincia` varchar(45) NOT NULL,
  `pais` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE unique_localidad(localidad,provincia,pais)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `personas`
(
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `email` varchar(100) NULL,
  `fechaCumpleanios` date NULL,
  `idDireccion` int(10) NULL,
  `tipoContacto` varchar(45) NULL,
  `idLocalidad` int(10) NULL,
  PRIMARY KEY (`idPersona`),
  UNIQUE unique_tel(telefono)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

alter table agenda_oscar_antonio.personas
add  FOREIGN KEY (idDireccion) REFERENCES direccion (idDireccion);

alter table agenda_oscar_antonio.personas
add  FOREIGN KEY (idLocalidad) REFERENCES localidad (id);

INSERT INTO agenda_oscar_antonio.localidad (localidad,provincia,pais) 
VALUES('PABLO NOGUES','BUENOS AIRES','ARGENTINA');

INSERT INTO agenda_oscar_antonio.localidad (localidad,provincia,pais) 
VALUES('BUZIOS','RIO DE JANEIRO','BRASIL');

INSERT INTO agenda_oscar_antonio.localidad (localidad,provincia,pais) 
VALUES('CABO FRIO','RIO DE JANEIRO','BRASIL');

INSERT INTO agenda_oscar_antonio.direccion (idDireccion,calle,altura)  
VALUES(1,'PERALTA RAMOS','2123');

INSERT INTO agenda_oscar_antonio.personas (idPersona,nombre,apellido,telefono,email,fechaCumpleanios,tipoContacto) 
VALUES(1,'OSCAR','UMBERT','423423','OSCAR@GMAIL.COM',CURDATE(),'AMIGO');

INSERT INTO agenda_oscar_antonio.personas (idPersona,nombre,apellido,telefono,email,fechaCumpleanios,tipoContacto,idDireccion,idLocalidad) 
VALUES(2,'ANTONIO','LIENDRO','4243423','ANTONIO@GMAIL.COM',CURDATE(),'FAMILIA',1,1);


INSERT INTO agenda_oscar_antonio.localidad (localidad,provincia,pais) 
VALUES('BUZIOS','RIO DE JANEIRO','BRASIL');

INSERT INTO agenda_oscar_antonio.localidad (localidad,provincia,pais) 
VALUES('CABO FRIO','RIO DE JANEIRO','BRASIL');

INSERT INTO agenda_oscar_antonio.direccion (idDireccion,calle,altura)  
VALUES(1,'PERALTA RAMOS','2123');

INSERT INTO agenda_oscar_antonio.personas (idPersona,nombre,apellido,telefono,email,fechaCumpleanios,tipoContacto) 
VALUES(1,'OSCAR','UMBERT','423423','OSCAR@GMAIL.COM',CURDATE(),'AMIGO');

INSERT INTO agenda_oscar_antonio.personas (idPersona,nombre,apellido,telefono,email,fechaCumpleanios,tipoContacto,idDireccion,idLocalidad) 
VALUES(2,'ANTONIO','LIENDRO','4243423','ANTONIO@GMAIL.COM',CURDATE(),'FAMILIA',1,1);


commit;