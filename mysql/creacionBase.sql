DROP DATABASE IF EXISTS agencia;
CREATE DATABASE agencia;
USE agencia;
CREATE TABLE empleado(
usuario varchar(20),
nombre varchar(20),
apellido varchar(20),
cargo varchar(20),
clave varchar(20),
primary key(usuario)
);