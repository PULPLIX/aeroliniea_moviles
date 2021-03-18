host cls

drop table Usuario cascade constraint;
drop table Horarios;
drop table Ciudad;
drop table Avion;
drop table Rutas cascade constraint; //
drop table Vuelos cascade constraint; //
drop table Tiquetes; //

set linesize 300;
set pagesize 300;

-- -----------------------------------------------------
-- Table Horarios
-- -----------------------------------------------------
CREATE TABLE Horarios (
  id INT NOT NULL,
  dia_semana VARCHAR2(45) NULL,
  hora_llegada TIME NULL,
  constraint PKHorario primary key(id);


-- -----------------------------------------------------
-- Table Ciudad
-- -----------------------------------------------------
CREATE TABLE Ciudad (
  id INT NOT NULL,
  nombre VARCHAR2(250) NULL,
  constraint PKCiudad primary key(id);

-- -----------------------------------------------------
-- Table Rutas
-- -----------------------------------------------------
CREATE TABLE Rutas (
  id INT NOT NULL,
  duracion INT NOT NULL,
  horario_id INT NOT NULL,
  ciudad_origen INT NOT NULL,
  ciudad_destino INT NOT NULL,
  precio DOUBLE NULL,
  porcentaje_descuento DOUBLE NULL,
  CONSTRAINT PKRutas primary key(id),	
  CONSTRAINT fk_rutas_horarios1 
	FOREIGN KEY (horario_id) 
	REFERENCES Horarios (id),
  CONSTRAINT fk_rutas_pais1
    FOREIGN KEY (ciudad_destino)
    REFERENCES ciudad (id),
  CONSTRAINT fk_rutas_pais2
    FOREIGN KEY (ciudad_origen)
    REFERENCES ciudad (id);

-- -----------------------------------------------------
-- Table Usuarios
-- -----------------------------------------------------
CREATE TABLE Usuarios (
  id INT NOT NULL,
  contrasena VARCHAR2(45) NULL,
  nombre VARCHAR2(45) NULL,
  apellidos VARCHAR2(45) NULL,
  correo VARCHAR2(45) NULL,
  fecha_nacimiento DATE NULL,
  direccion VARCHAR2(45) NULL,
  telefono_trabajo VARCHAR2(60) NULL,
  celular VARCHAR2(60) NULL,
  rol INT NULL,
  CONSTRAINT PKUsuarios primary key(id));

-- -----------------------------------------------------
-- Table Aviones
-- -----------------------------------------------------
CREATE TABLE Aviones (
  id INT NOT NULL AUTO_INCREMENT,
  tipo VARCHAR2(45) NULL,
  capacidad INT NULL,
  anio INT NULL,
  marca VARCHAR2(45) NULL,
  asientos_fila INT NULL,
  cantidad_filas INT NULL,
  CONSTRAINT PKAviones primary key(id));

-- -----------------------------------------------------
-- Table Vuelos
-- -----------------------------------------------------
CREATE TABLE Vuelos (
  id INT NOT NULL AUTO_INCREMENT,
  modalidad VARCHAR2(45) NULL,
  duracion INT NULL, --REVUSAR LA DURACION
  ruta_id INT NOT NULL,
  avion_id INT NOT NULL,
  fecha DATE NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_vuelos_rutas1
    FOREIGN KEY (ruta_id)
    REFERENCES Rutas (id),
  CONSTRAINT fk_vuelos_aviones1
    FOREIGN KEY (avion_id)
    REFERENCES Aviones (id));

-- -----------------------------------------------------
-- Table Tiquetes
-- -----------------------------------------------------
CREATE TABLE Tiquetes (
  id INT NOT NULL,
  usuario_id INT NOT NULL,
  vuelo_id INT NOT NULL,
  precio_final VARCHAR2(45) NULL,
  fila_asisento INT NULL,
  columna_asiento INT NULL,
  forma_pago VARCHAR2(45) NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_tiquete_usuario1
    FOREIGN KEY (usuario_id)
    REFERENCES Usuarios (id),
  CONSTRAINT fk_tiquetes_vuelos1
    FOREIGN KEY (vuelo_id)
    REFERENCES Vuelos (id));

------------------------------------------------------------------------------------------
create or replace package TYPES
as
type ref_cursor is ref cursor;
end;
/
show error
------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------
create or replace procedure INSERCION_USUARIO(
ArgId in VARCHAR2,
ArgContra in VARCHAR2,
ArgNombre in VARCHAR2,
ArgApellido in VARCHAR2,
ArgCorreo in VARCHAR2,
ArgFecha_N in VARCHAR2,
ArgDireccion in VARCHAR2,
ArgTelefono in VARCHAR2,
ArgCelular in VARCHAR2,
ArgRol in NUMBER ) as
begin
	insert into Usuarios(id,contrasena,nombre,apellidos,correo,fecha_nacimiento,direccion,telefono_trabajo,celular,rol) values (ArgId,ArgContra,ArgNombre,ArgApellido,ArgCorreo,ArgFecha_N,ArgDireccion,ArgTelefono,ArgCelular,ArgRol);
	commit;
end INSERCION_USUARIO;
/
show error
------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------
create or replace procedure UPDATE_USUARIO(
ArgCedula in VARCHAR2,
ArgClave in VARCHAR2, 
ArgRol in NUMBER ) as
begin
	update Usuario set clave=ArgClave , rol=ArgRol where cedula = ArgCedula;
	commit;
end UPDATE_USUARIO;
/
show error
------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------
create or replace procedure GET_RUTAS()
begin
	SELECT * FROM Rutas;
	commit;
end INSERCION_RUTA;
/
show error
------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------
create or replace procedure INSERCION_RUTA(
ArgId in INT,
ArgDuracion in INT,
ArgHorario in INT,
ArgCiudadOrg in INT,
ArgCiudadDest in INT,
ArgPrecio in DOUBLE,
ArgDescuento in DOUBLE)
begin
	insert into Horarios values (ArgId,ArgDuracion,ArgHorario,ArgCiudadOrg,ArgCiudadDest,ArgPrecio,ArgDescuento);
	commit;
end INSERCION_RUTA;
/
show error
------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------
create or replace procedure UPDATE_RUTA(
ArgId in INT,
ArgDuracion in INT,
ArgHorario in INT,
ArgCiudadOrg in INT,
ArgCiudadDest in INT,
ArgPrecio in DOUBLE,
ArgDescuento in DOUBLE) as
begin
	update Usuario set duracion=ArgDuracion , ciudad_destino=ArgCiudadOrg,  ciudad_destino=ArgCiudadDest, precio=ArgPrecio, porcentaje_descuento=ArgDescuento where id = ArgId;
	commit;
end UPDATE_USUARIO;
/
show error
------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------
create or replace procedure DELETE_RUTA(
ArgId in INT) as
begin
	DELETE from rutas where id = ArgId;
	commit;
end UPDATE_USUARIO;
/
show error
------------------------------------------------------------------------------------------