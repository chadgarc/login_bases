create user 'adminbdmundotravel' identified by '1234';
grant all privileges on bdmundotravel.* to 'adminbdmundotravel';

DROP DATABASE IF EXISTS bdmundotravel;
CREATE DATABASE bdmundotravel;
USE bdmundotravel;

CREATE TABLE empleado(
usuario varchar(20),
nombre varchar(20),
apellido varchar(20),
cargo varchar(20),
clave varchar(20),
primary key(usuario)
);
create table cliente(
idCliente varchar(10),
nombre  varchar(45),
apellido varchar(45),
fechaNacimiento varchar(45),
primary key(idCliente)
);
create table paqueteDeViaje(
idPaquete int auto_increment,
nombre varchar(50),
descripcion varchar(200),
primary key(idPaquete)
);
create table registro(
idCliente varchar(10),
idPaquete int,
primary key (idCliente,idPaquete),
foreign key (idCliente) references cliente(idCliente),
foreign key (idPaquete) references paqueteDeViaje(idPaquete)
);

create table direccion(
idDireccion varchar(10),
direccion1 varchar(50),
direccion2 varchar(50),
direccion3 varchar(50),
telefono varchar(15),
ciudad varchar(25),
pais varchar(20),
primary key(idDireccion)
);

create table reservaHotel(
idReservaHotel int auto_increment,
idCliente varchar(10),
fechaIngreso date,
fechaSalida date,
idPaquete int,

primary key (idReservaHotel),
foreign key(idCliente) references cliente(idCLiente),
foreign key(idPaquete) references paqueteDeViaje(idPaquete)
);

create table  reservaVuelo(
idReserva int auto_increment,
idCliente varchar(10),
NumeroVuelos varchar(2),
clase varchar(10),
idPaquete int,

primary key (idReserva),
foreign key(idCliente) references cliente(idCliente),
foreign key(idPaquete) references paqueteDeViaje(idPaquete)
);
#unir vuelos

create table vuelo(
idVuelo int auto_increment,
aerolinea varchar(20),
ciudadOrigen varchar(25),
ciudadDestino varchar(25),
fechaSalida varchar(10),
fechaRetorno varchar(10),
horaVuelo time,

primary key(idVuelo)
);

alter table reservaVuelo
add column idVuelo int;
alter table reservaVuelo
add constraint fk_idVuelo foreign key (idVuelo) references vuelo(idVuelo);

delimiter $$
create procedure crearEmpleado(IN usuario varchar(20), IN nombre varchar(20), IN apellido varchar(20), IN cargo varchar(20), IN clave varchar(20))
BEGIN
INSERT INTO empleado values(usuario,nombre,apellido,cargo,clave);
end;$$

create view conteoRegistros as
select p.nombre as nombre, count(r.idCliente) as conteo
from registro r right join paquetedeviaje p on r.idPaquete = p.idPaquete
group by p.idPaquete;