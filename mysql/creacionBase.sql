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
fechaNacimiento date,
primary key(idCliente)
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
create table vehiculo(
idVehiculo varchar(15),
licencia varchar(10),
nombreRentadora varchar(20),
numeroDias varchar(2),
fechaIngreso date,
fechaDevolucion date,
idDireccionIngreso varchar(10),
idDireccionRetorno varchar(10),
idCliente varchar(10),

primary key (idVehiculo),
foreign key(idCliente) references cliente(idCLiente),
foreign key(idDireccionIngreso) references direccion(idDireccion),
foreign key(idDireccionRetorno) references direccion(idDireccion)
);
create table reservaHotel(
idReservaHotel varchar(10),
idCliente varchar(10),
fechaIngreso date,
fechaSalida date,

primary key (idReservaHotel),
foreign key(idCliente) references cliente(idCLiente)
);
#unir hotel con reservaHotel
create table hotel(
idHotel varchar(10),
nombre varchar(30),
direccion varchar(10),
categoria varchar(10),

primary key(idHotel),
foreign key(direccion) references direccion(idDireccion)
);

alter table reservaHotel
add column idHotel varchar(10);
alter table reservaHotel
add constraint fk_idHotel foreign key (idHotel) references hotel(idHotel);

create table  reservaVuelo(
idReserva varchar(10),
idCliente varchar(10),
NumeroVuelos varchar(2),
CantidadDeViajes varchar(2),
clase varchar(10),

primary key (idReserva),
foreign key(idCliente) references cliente(idCliente)
);
#unir vuelos

create table vuelo(
idVuelo varchar(10),
aerolinea varchar(20),
ciudadOrigen varchar(25),
ciudadDestino varchar(25),
fechaSalida date,
fechaRetorno date,
horaVuelo time,
idReserva varchar(10),

primary key(idVuelo),
foreign key(idReserva) references reservaVuelo(idReserva)
);

alter table reservaVuelo
add column idVuelo varchar(10);
alter table reservaVuelo
add constraint fk_idVuelo foreign key (idVuelo) references vuelo(idVuelo);

create table crucero(
idCrucero varchar(15),
cabina varchar(15),
nave varchar(25),
aerolinea varchar(25),
puerto varchar(20),
direccionPuerto varchar(10),
idCliente varchar(10),
NumeroDias varchar(2),
detalle varchar(150),

primary key(idCrucero),
foreign key(direccionPuerto) references direccion(idDireccion),
foreign key(idCliente) references cliente(idCliente)
);








