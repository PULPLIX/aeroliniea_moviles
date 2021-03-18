host cls

drop table Usuario cascade constraint;
drop table Horarios;
drop table Ciudad;
drop table Avion;
drop table Rutas cascade constraint;
drop table Vuelos cascade constraint;
drop table Tiquetes;

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
  duracion TIME NOT NULL,
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
  duracion TIME NULL,
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
-- >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Procedimientos <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
------------------------------------------------------------------------------------------

create or replace package TYPES
as
type ref_cursor is ref cursor;
end;
/
show error
------------------------------------------------------------------------------------------
-- ************************************* Usuarios ************************************
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

create or replace procedure UPDATE_USUARIO(
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
	update Usuarios set contrasena=ArgContra,nombre=ArgNombre,apellidos=ArgApellido,correo=ArgCorreo,direccion=ArgDireccion,telefono_trabajo=ArgTelefono,celular=ArgCelular,rol=ArgRol where id = ArgId;
	commit;
end UPDATE_USUARIO;
/
show error
------------------------------------------------------------------------------------------

create or replace procedure DELETE_USUARIO(ArgId in VARCHAR2) as
begin
	delete Usuarios where id = ARgId;	
	commit;
end DELETE_USUARIO;
/
show error
------------------------------------------------------------------------------------------

create or replace function GET_USUARIO(ArgId in VARCHAR2)
return TYPES.ref_cursor
as
usuario_cursor TYPES.ref_cursor;
begin
	open usuario_cursor for
	select id,contrasena,nombre,apellidos,correo,direccion,telefono_trabajo,celular,rol from Usuarios where id = ArgId;	
	return usuario_cursor;
end GET_USUARIO;
/
show error
------------------------------------------------------------------------------------------

create or replace function LISTAR_USUARIO 
return TYPES.ref_cursor
as
usuario_cursor TYPES.ref_cursor;
begin
	open usuario_cursor for
	select id,nombre,apellidos,correo,direccion,celular,rol from USUARIOS;
	return usuario_cursor;
end LISTAR_USUARIO;
/
show error
------------------------------------------------------------------------------------------
-- ************************************* Horarios ************************************
------------------------------------------------------------------------------------------

create or replace procedure INSERCION_HORARIO(
ArgId in VARCHAR2,
ArgDia_semana in VARCHAR2,
ArgHora_llegada in NUMBER) as
begin
	insert into Horarios(id,dia_semana,hora_llegada) values (ArgId,ArgDia_semana,ArgHora_llegada);
	commit;
end INSERCION_HORARIO;
/
show error
------------------------------------------------------------------------------------------

create or replace procedure UPDATE_HORARIO(
ArgId in VARCHAR2,
ArgDia_semana in VARCHAR2,
ArgHora_llegada in NUMBER) as
begin
	update Horarios set dia_semana=ArgDia_semana,hora_llegada=ArgHora_llegada where id = ArgId;
	commit;
end UPDATE_HORARIO;
/
show error
------------------------------------------------------------------------------------------

create or replace procedure DELETE_HORARIO(ArgId in VARCHAR2) as
begin
	delete Horarios where id = ARgId;	
	commit;
end DELETE_HORARIO;
/
show error
------------------------------------------------------------------------------------------

create or replace function GET_HORARIO(ArgId in VARCHAR2)
return TYPES.ref_cursor
as
horario_cursor TYPES.ref_cursor;
begin
	open horario_cursor for
	select id,dia_semana,hora_llegada from Horarios where id = ArgId;	
	return horario_cursor;
end GET_HORARIO;
/
show error
------------------------------------------------------------------------------------------

create or replace function LISTAR_HORARIO 
return TYPES.ref_cursor
as
horario_cursor TYPES.ref_cursor;
begin
	open horario_cursor for
	select id,dia_semana,hora_llegada from Horarios;
	return horario_cursor;
end LISTAR_HORARIO;
/
show error
------------------------------------------------------------------------------------------
-- ************************************* Ciudad ************************************
------------------------------------------------------------------------------------------

create or replace procedure INSERCION_CIUDAD(
ArgId in VARCHAR2,
ArgNombre in VARCHAR2) as
begin
	insert into CIUDAD(id,nombre) values (ArgId,ArgNombre);
	commit;
end INSERCION_CIUDAD;
/
show error

------------------------------------------------------------------------------------------
create or replace procedure UPDATE_CIUDAD(
ArgId in VARCHAR2,
ArgNombre in VARCHAR2) as
begin
	update CIUDAD set nombre=ArgNombre where id = ArgId;
	commit;
end UPDATE_CIUDAD;
/
show error
------------------------------------------------------------------------------------------

create or replace procedure DELETE_CIUDAD(ArgId in VARCHAR2) as
begin
	delete CIUDAD where id = ARgId;	
	commit;
end DELETE_CIUDAD;
/
show error
------------------------------------------------------------------------------------------

create or replace function GET_CIUDAD(ArgId in VARCHAR2)
return TYPES.ref_cursor
as
ciudad_cursor TYPES.ref_cursor;
begin
	open ciudad_cursor for
	select id,nombre from Ciudad where id = ArgId;	
	return ciudad_cursor;
end GET_CIUDAD;
/
show error
------------------------------------------------------------------------------------------

create or replace function LISTAR_CIUDAD 
return TYPES.ref_cursor
as
ciudad_cursor TYPES.ref_cursor;
begin
	open ciudad_cursor for
	select id,nombre from CIUDAD;
	return ciudad_cursor;
end LISTAR_CIUDAD;
/
show error
------------------------------------------------------------------------------------------
-- ************************************* Aviones ************************************
------------------------------------------------------------------------------------------
create or replace procedure INSERCION_AVIONES(
ArgId in VARCHAR2,
ArgTipo in VARCHAR2,
ArgCapacidad in NUMBER,
ArgAnio in NUMBER,
ArgMarca in VARCHAR2,
ArgAsientos_fila in NUMBER,
ArgCantidad_filas in NUMBER) as
begin
	insert into AVIONES(id,tipo,capacidad,anio,marca,asientos_fila,cantidad_filas) values (ArgId,ArgTipo,ArgCapacidad,ArgAnio,ArgMarca,ArgAsientos_fila,ArgCantidad_filas);
	commit;
end INSERCION_AVIONES;
/
show error
------------------------------------------------------------------------------------------

create or replace procedure UPDATE_AVIONES(
ArgId in VARCHAR2,
ArgTipo in VARCHAR2,
ArgCapacidad in NUMBER,
ArgAnio in NUMBER,
ArgMarca in VARCHAR2,
ArgAsientos_fila in NUMBER,
ArgCantidad_filas in NUMBER) as
begin
	update AVIONES set tipo=ArgTipo,capacidad=ArgCapacidad,anio=ArgAnio,marca=ArgMarca,asientos_fila=ArgAsientos_fila,cantidad_filas=ArgCantidad_filas where id = ArgId;
	commit;
end UPDATE_AVIONES;
/
show error
------------------------------------------------------------------------------------------

create or replace procedure DELETE_AVIONES(ArgId in VARCHAR2) as
begin
	delete AVIONES where id = ARgId;	
	commit;
end DELETE_AVIONES;
/
show error
------------------------------------------------------------------------------------------

create or replace function GET_AVIONES(ArgId in VARCHAR2)
return TYPES.ref_cursor
as
avion_cursor TYPES.ref_cursor;
begin
	open avion_cursor for
	select id,tipo,capacidad,anio,marca,asientos_fila,cantidad_filas from Aviones where id = ArgId;	
	return avion_cursor;
end GET_AVIONES;
/
show error
------------------------------------------------------------------------------------------

create or replace function LISTAR_AVIONES 
return TYPES.ref_cursor
as
avion_cursor TYPES.ref_cursor;
begin
	open avion_cursor for
	select id,tipo,capacidad,anio,marca,asientos_fila,cantidad_filas from Aviones;
	return avion_cursor;
end LISTAR_AVIONES;
/
show error


-- 0 admin / 1 user
execute INSERCION_USUARIO('12345678','admin','David','Cordero Aguilar','David@gmial.com',sysdate,'Heredia','8888888','78787878',0);

execute UPDATE_USUARIO('12345678','admin','David','Cordero Aguilar','lol@gmial.com',sysdate,'San Carlos','88888888','77777777',1);


variable x refcursor
exec :x:= GET_USUARIO('12345678');
print x;

variable x refcursor
exec :x:= LISTAR_USUARIO;
print x;
