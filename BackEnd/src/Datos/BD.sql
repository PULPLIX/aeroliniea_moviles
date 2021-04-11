host cls

drop table Horarios cascade constraints;
drop table Ciudad cascade constraints;
drop table Rutas cascade constraints; 
drop table Usuarios cascade constraints;
drop table Aviones cascade constraints;
drop table Vuelos cascade constraints; 
drop table Tiquetes cascade constraints; 

drop sequence seq_horarios;
drop sequence seq_cuidad;
drop sequence seq_rutas;
drop sequence seq_aviones;
drop sequence seq_vuelos;
drop sequence seq_tiquetes;


set linesize 300;
set pagesize 300;

-- -----------------------------------------------------
-- Table Horarios
-- -----------------------------------------------------
CREATE TABLE Horarios (
  id INT NOT NULL,
  dia_semana VARCHAR2(45) NULL,
  hora_llegada INT NULL,
  constraint PKHorario primary key(id));

	

CREATE SEQUENCE seq_horarios MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 10;

-- -----------------------------------------------------
-- Table Ciudad
-- -----------------------------------------------------
CREATE TABLE Ciudad (
  id INT NOT NULL,
  nombre VARCHAR2(250) NULL,
  constraint PKCiudad primary key(id));

CREATE SEQUENCE seq_cuidad MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 10;

-- -----------------------------------------------------
-- Table Rutas
-- -----------------------------------------------------
CREATE TABLE Rutas (
  id INT NOT NULL,
  horario_id INT NOT NULL,
  ciudad_origen INT NOT NULL,
  ciudad_destino INT NOT NULL,
  precio NUMBER(7,2) NULL,
  porcentaje_descuento NUMBER(7,2) NULL,
  CONSTRAINT PKRutas primary key(id),	
  CONSTRAINT fk_rutas_horarios1 
	FOREIGN KEY (horario_id) 
	REFERENCES Horarios (id),
  CONSTRAINT fk_rutas_pais1
    FOREIGN KEY (ciudad_destino)
    REFERENCES ciudad (id),
  CONSTRAINT fk_rutas_pais2
    FOREIGN KEY (ciudad_origen)
    REFERENCES ciudad (id));

CREATE SEQUENCE seq_rutas MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 10;
-- -----------------------------------------------------
-- Table Usuarios
-- -----------------------------------------------------
CREATE TABLE Usuarios (
  id VARCHAR(100) NOT NULL,
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
  id INT NOT NULL,
  tipo VARCHAR2(45) NULL,
  capacidad INT NULL,
  anio INT NULL,
  marca VARCHAR2(45) NULL,
  asientos_fila INT NULL,
  cantidad_filas INT NULL,
  CONSTRAINT PKAviones primary key(id));

CREATE SEQUENCE seq_aviones MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 10;
-- -----------------------------------------------------
-- Table Vuelos
-- -----------------------------------------------------
CREATE TABLE Vuelos (
  id INT NOT NULL ,
  modalidad VARCHAR2(45) NULL,
  duracion INT NULL, 
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
			
CREATE SEQUENCE seq_vuelos MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 10;
-- -----------------------------------------------------
-- Table Tiquetes
-- -----------------------------------------------------
CREATE TABLE Tiquetes (
  id INT NOT NULL,
  usuario_id VARCHAR2(100) NOT NULL,
  vuelo_id INT NOT NULL,
  precio_final VARCHAR2(45) NULL,
  fila_asiento INT NULL,
  columna_asiento INT NULL,
  forma_pago VARCHAR2(45) NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_tiquete_usuario1
    FOREIGN KEY (usuario_id)
    REFERENCES Usuarios (id),
  CONSTRAINT fk_tiquetes_vuelos1
    FOREIGN KEY (vuelo_id)
    REFERENCES Vuelos (id));
		       
CREATE SEQUENCE seq_tiquetes MINVALUE 1 START WITH 1 INCREMENT BY 1 CACHE 10;

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
	insert into Usuarios(id,contrasena,nombre,apellidos,correo,fecha_nacimiento,direccion,telefono_trabajo,celular,rol) values (ArgId,ArgContra,ArgNombre,ArgApellido,ArgCorreo,TO_DATE(ArgFecha_N, 'DD/MM/YYYY HH:MI:SS'),ArgDireccion,ArgTelefono,ArgCelular,ArgRol);
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
-------------------------------------------------------------------------------------------

create or replace function GET_USUARIO(ArgId in VARCHAR2)
return TYPES.ref_cursor
as
usuario_cursor TYPES.ref_cursor;
begin
	open usuario_cursor for
	select id,contrasena,nombre,apellidos,correo,fecha_nacimiento,direccion,telefono_trabajo,celular,rol from Usuarios where id = ArgId;	
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
	select id,contrasena,nombre,apellidos,correo,fecha_nacimiento,direccion,telefono_trabajo,celular,rol from USUARIOS;
	return usuario_cursor;
end LISTAR_USUARIO;
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
create or replace function VALIDA_USUARIO(ArgId in VARCHAR2, ArgClave in VARCHAR2)
return TYPES.ref_cursor
as
usuario_cursor TYPES.ref_cursor;
begin
	open usuario_cursor for
	select id,rol from Usuarios where id = ArgId and contrasena=ArgClave;	
	return usuario_cursor;
end Valida_Usuario;
/
show errors
------------------------------------------------------------------------------------------
-- !****************************** PROCEDIMIENTOS DE RUTAS ********************************
------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------
create or replace function LISTAR_RUTAS
return TYPES.ref_cursor
as
rutas_cursor TYPES.ref_cursor;
begin
  OPEN rutas_cursor for
	select r.id id_ruta, r.precio, r.porcentaje_descuento, c.id id_origen,c.nombre nombre_origen, c2.id id_destino, c2.nombre nombre_destino, h.id id_horario, h.dia_semana, h.hora_llegada
            from rutas r
            inner join ciudad c on r.ciudad_origen = c.id 
            inner join ciudad c2 on r.ciudad_destino = c2.id 
            inner join horarios h on r.horario_id = h.id;
  return rutas_cursor;
end LISTAR_RUTAS;
/
show error
------------------------------------------------------------------------------------------
create or replace function GET_RUTA(ArgId in VARCHAR2)
return TYPES.ref_cursor
as
ruta_cursor TYPES.ref_cursor;
begin
  OPEN ruta_cursor for
    select r.id id_ruta, r.precio, r.porcentaje_descuento, c.id id_origen,c.nombre nombre_origen, c2.id id_destino, c2.nombre nombre_destino, h.id id_horario, h.dia_semana, h.hora_llegada
        from rutas r
        inner join ciudad c on r.ciudad_origen = c.id 
        inner join ciudad c2 on r.ciudad_destino = c2.id 
        inner join horarios h on r.horario_id = h.id
        where r.id = ArgId;
    return ruta_cursor;
end GET_RUTA;
/
show error
------------------------------------------------------------------------------------------
create or replace procedure INSERCION_RUTA(
ArgHorario in INT,
ArgCiudadOrg in INT,
ArgCiudadDest in INT,
ArgPrecio in NUMBER,
ArgDescuento in NUMBER)
as
begin
	insert into rutas values (seq_rutas.nextval,ArgHorario,ArgCiudadOrg,ArgCiudadDest,ArgPrecio,ArgDescuento);
	commit;
end INSERCION_RUTA;
/
show error
------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------
create or replace procedure UPDATE_RUTA(
ArgId in INT,
ArgHorario in INT,
ArgCiudadOrg in INT,
ArgCiudadDest in INT,
ArgPrecio in NUMBER,
ArgDescuento in NUMBER) as
begin
	update rutas set  horario_id=ArgHorario, ciudad_destino=ArgCiudadOrg,  ciudad_origen=ArgCiudadDest, precio=ArgPrecio, porcentaje_descuento=ArgDescuento where id = ArgId;
	commit;
end UPDATE_RUTA;
/
show error
------------------------------------------------------------------------------------------
create or replace procedure DELETE_RUTA(
ArgId in INT) as
begin
	DELETE from rutas where id = ArgId;
	commit;
end DELETE_RUTA;
/
show error
------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------
-- ****************************** PROCEDIMIENTOS DE vuelos ********************************
------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------
create or replace function LISTAR_VUELOS
return TYPES.ref_cursor
as
vuelos_cursor TYPES.ref_cursor;
begin
  OPEN vuelos_cursor for
        select v.id id_vuelo, v.modalidad, v.duracion, v.fecha, r.id id_ruta, r.precio, r.porcentaje_descuento, 
        c.id id_origen, c.nombre nombre_origen, c2.id id_destino, c2.nombre nombre_destino, h.id id_horario, h.dia_semana, h.hora_llegada,
        a.id id_avion, a.tipo, a.capacidad, a.anio, a.marca, a.asientos_fila, a.cantidad_filas
        from vuelos v
            inner join rutas r on r.id = v.ruta_id
            inner join ciudad c on r.ciudad_origen = c.id
            inner join ciudad c2 on r.ciudad_destino = c2.id
            inner join horarios h on r.horario_id = h.id
            inner join aviones a on a.id = v.avion_id;
  return vuelos_cursor;
end LISTAR_VUELOS;
/
show error
------------------------------------------------------------------------------------------
create or replace function GET_VUELO(ArgId in VARCHAR2)
return TYPES.ref_cursor
as
vuelo_cursor TYPES.ref_cursor;
begin
	open vuelo_cursor for
                select v.id id_vuelo, v.modalidad, v.duracion, v.fecha, r.id id_ruta, r.precio, r.porcentaje_descuento, 
                c.id id_origen, c.nombre nombre_origen, c2.id id_destino, c2.nombre nombre_destino, h.id id_horario, h.dia_semana, h.hora_llegada,
                a.id id_avion, a.tipo, a.capacidad, a.anio, a.marca, a.asientos_fila, a.cantidad_filas
                from vuelos v
                    inner join rutas r on r.id = v.ruta_id
                    inner join ciudad c on r.ciudad_origen = c.id
                    inner join ciudad c2 on r.ciudad_destino = c2.id
                    inner join horarios h on r.horario_id = h.id
                    inner join aviones a on a.id = v.avion_id
                    where v.id = ArgId;
	return vuelo_cursor;
end GET_VUELO;
/
show error
------------------------------------------------------------------------------------------

create or replace function FILTRAR_VUELO(ArgModalidad in VARCHAR2,ArgOrigen in VARCHAR2, ArgDestino in VARCHAR2, ArgFechaI in VARCHAR2, ArgFechaF in VARCHAR2)
return TYPES.ref_cursor
as
vuelo_cursor TYPES.ref_cursor;
begin
	open vuelo_cursor for
                select v.id id_vuelo, v.modalidad, v.duracion, v.fecha, r.id id_ruta, r.precio, r.porcentaje_descuento, 
                c.id id_origen, c.nombre nombre_origen, c2.id id_destino, c2.nombre nombre_destino, h.id id_horario, h.dia_semana, h.hora_llegada,
                a.id id_avion, a.tipo, a.capacidad, a.anio, a.marca, a.asientos_fila, a.cantidad_filas
                from vuelos v
                    inner join rutas r on r.id = v.ruta_id
                    inner join ciudad c on r.ciudad_origen = c.id
                    inner join ciudad c2 on r.ciudad_destino = c2.id
                    inner join horarios h on r.horario_id = h.id
                    inner join aviones a on a.id = v.avion_id
                    where c.id = ArgOrigen
                    and c2.id = ArgDestino
                    and v.modalidad = ArgModalidad
                    and v.fecha between TO_DATE(ArgFechaI, 'MM/DD/YYYY HH:MI:SS' ) and TO_DATE(ArgFechaF, 'MM/DD/YYYY HH:MI:SS' );
	return vuelo_cursor;
end FILTRAR_VUELO;
/
show error
------------------------------------------------------------------------------------------

create or replace function FILTRAR_VUELODESCUENTO(ArgModalidad in VARCHAR2,ArgOrigen in VARCHAR2, ArgDestino in VARCHAR2, ArgFechaI in VARCHAR2, ArgFechaF in VARCHAR2)
return TYPES.ref_cursor
as
vuelo_cursor TYPES.ref_cursor;
begin
	open vuelo_cursor for
                select v.id id_vuelo, v.modalidad, v.duracion, v.fecha, r.id id_ruta, r.precio, r.porcentaje_descuento, 
                c.id id_origen, c.nombre nombre_origen, c2.id id_destino, c2.nombre nombre_destino, h.id id_horario, h.dia_semana, h.hora_llegada,
                a.id id_avion, a.tipo, a.capacidad, a.anio, a.marca, a.asientos_fila, a.cantidad_filas
                from vuelos v
                    inner join rutas r on r.id = v.ruta_id
                    inner join ciudad c on r.ciudad_origen = c.id
                    inner join ciudad c2 on r.ciudad_destino = c2.id
                    inner join horarios h on r.horario_id = h.id
                    inner join aviones a on a.id = v.avion_id
                    where c.id = ArgOrigen
                    and c2.id = ArgDestino
                    and v.modalidad = ArgModalidad
                    and r.porcentaje_descuento > 0
                    and v.fecha between TO_DATE(ArgFechaI, 'MM/DD/YYYY HH:MI:SS') and TO_DATE(ArgFechaF, 'MM/DD/YYYY HH:MI:SS');
	return vuelo_cursor;
end FILTRAR_VUELODESCUENTO;
/
show error
------------------------------------------------------------------------------------------



create or replace procedure INSERCION_VUELOS(
ArgModalidad in INT,
ArgDuracion in INT,
ArgRuta in INT,
ArgAvion in INT,
ArgFecha in VARCHAR)
as
begin
	insert into vuelos values (seq_vuelos.nextval,ArgModalidad,ArgDuracion,ArgRuta,ArgAvion,TO_DATE(ArgFecha, 'DD/MM/YYYY HH:MI:SS'));
	commit;
end INSERCION_VUELOS;
/
show error

------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------
create or replace procedure UPDATE_VUELO(
ArgId in INT,
ArgModalidad in INT,
ArgDuracion in INT,
ArgRuta in INT,
ArgAvion in INT,
ArgFecha in VARCHAR) as
begin
	update Vuelos set modalidad=ArgModalidad , duracion=ArgDuracion, ruta_id=ArgRuta,  avion_id=ArgAvion, fecha=ArgFecha where id = ArgId;
	commit;
end UPDATE_VUELO;
/
show error

------------------------------------------------------------------------------------------
create or replace procedure DELETE_VUELO(
ArgId in INT) as
begin
	DELETE from vuelos where id = ArgId;
	commit;
end DELETE_VUELO;
/
show error
------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------
-- ************************************* Horarios ************************************
------------------------------------------------------------------------------------------

create or replace procedure INSERCION_HORARIO(
ArgDia_semana in VARCHAR2,
ArgHora_llegada in NUMBER) as
begin
	insert into horarios values (seq_horarios.nextval,ArgDia_semana,ArgHora_llegada);
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

create or replace procedure INSERCION_CIUDAD(
ArgNombre in VARCHAR2) as
begin
	insert into ciudad values (seq_cuidad.nextval,ArgNombre);
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
ArgTipo in VARCHAR2,
ArgCapacidad in NUMBER,
ArgAnio in NUMBER,
ArgMarca in VARCHAR2,
ArgAsientos_fila in NUMBER,
ArgCantidad_filas in NUMBER) as
begin
	insert into aviones values (seq_aviones.nextval,ArgTipo,ArgCapacidad,ArgAnio,ArgMarca,ArgAsientos_fila,ArgCantidad_filas);
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

create or replace procedure DELETE_AVIONES(ArgId in INT) as
begin
	delete AVIONES where id = ARgId;	
	commit;
end DELETE_AVIONES;
/
show error
------------------------------------------------------------------------------------------

create or replace function GET_AVIONES(ArgId in INT)
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


------------------------------------------------------------------------------------------
-- ****************************** PROCEDIMIENTOS DE TIQUETES ********************************
------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------
create or replace function LISTAR_HISTORIAL(ArgId in VARCHAR2)
return TYPES.ref_cursor
as
tiquetes_cursor TYPES.ref_cursor;
begin
  OPEN tiquetes_cursor for
        select t.id id_tiquete, t.precio_final, t.fila_asiento, t.columna_asiento, t.forma_pago, 
        u.id id_usuario, u.nombre, u.apellidos, u.correo,u.fecha_nacimiento, u.direccion, u.telefono_trabajo, u.celular, u.rol,
        v.id id_vuelo, v.modalidad, v.duracion, v.fecha, 
        r.id id_ruta, r.precio, r.porcentaje_descuento,
        c.id id_origen, c.nombre nombre_origen, 
        c2.id id_destino, c2.nombre nombre_destino, 
        h.id id_horario, h.dia_semana, h.hora_llegada,
        a.id id_avion, a.tipo, a.capacidad, a.anio, a.marca, a.asientos_fila, a.cantidad_filas
        from tiquetes t
            inner join usuarios u on u.id = t.usuario_id
            inner join vuelos v on v.id = t.vuelo_id
            inner join rutas r on r.id = v.ruta_id
            inner join ciudad c on c.id = r.ciudad_origen
            inner join ciudad c2 on c2.id = r.ciudad_destino
            inner join horarios h on h.id = r.horario_id
            inner join aviones a on a.id = v.avion_id
            where t.id = ArgId;
  return tiquetes_cursor;
end LISTAR_HISTORIAL;
/
show error
------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------
create or replace function LISTAR_TIQUETES
return TYPES.ref_cursor
as
tiquetes_cursor TYPES.ref_cursor;
begin
  OPEN tiquetes_cursor for
	SELECT id,usuario_id,vuelo_id,precio_final,fila_asiento,columna_asiento,forma_pago FROM Tiquetes;
  return tiquetes_cursor;
end LISTAR_TIQUETES;
/
show error
------------------------------------------------------------------------------------------
create or replace function GET_TIQUETE(ArgId in VARCHAR2)
return TYPES.ref_cursor
as
tiquete_cursor TYPES.ref_cursor;
begin
	open tiquete_cursor for
	select * from Tiquetes where id = ArgId;	
	return tiquete_cursor;
end GET_TIQUETE;
/
show error
------------------------------------------------------------------------------------------
create or replace procedure INSERCION_TIQUETE(
ArgUsuario in VARCHAR2,
ArgVuelo in INT,
ArgPrecio in VARCHAR2,
ArgFila in INT,
ArgColumna in INT,
ArgFormaPago in VARCHAR2)
as
begin
	insert into Tiquetes values (seq_tiquetes.nextval,ArgUsuario,ArgVuelo,ArgPrecio,ArgColumna,ArgFila,ArgFormaPago);
	commit;
end INSERCION_TIQUETE;
/
show error

------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------

create or replace procedure UPDATE_TIQUETE(
ArgId in INT,
ArgUsuario in INT,
ArgVuelo in INT,
ArgPrecio in INT,
ArgFila in INT,
ArgColumna in INT,
ArgFormaPago in VARCHAR2)
as
begin
	update Tiquetes set usuario_id=ArgUsuario , vuelo_id=ArgVuelo,  precio_final=ArgPrecio, fila_asiento=ArgFila, columna_asiento=ArgColumna, forma_pago=ArgFormaPago where id = ArgId;
	commit;
end UPDATE_TIQUETE;
/
show error

------------------------------------------------------------------------------------------

create or replace procedure DELETE_TIQUETE(
ArgId in INT) as
begin
	DELETE from tiquetes where id = ArgId;
	commit;
end DELETE_TIQUETE;
/
show error
------------------------------------------------------------------------------------------

create or replace function HISTORIAL_TIQUETE(ArgUsuarioId in VARCHAR2)
return TYPES.ref_cursor
as
tiquete_cursor TYPES.ref_cursor;
begin
	open tiquete_cursor for
	select ID,USUARIO_ID,VUELO_ID,PRECIO_FINAL,FILA_ASIENTO,COLUMNA_ASIENTO,FORMA_PAGO from Tiquetes where USUARIO_ID = ArgUsuarioId;	
	return tiquete_cursor;
end HISTORIAL_TIQUETE;
/
show error

------------------------------------------------------------------------------------------
--                          PROCEDIMIENTOS PARA ASIENTOS
------------------------------------------------------------------------------------------
create or replace function FILAS_OCUPADAS(ArgIdVuelo in VARCHAR2)
return TYPES.ref_cursor
as
filas_cursor TYPES.ref_cursor;
begin
	open filas_cursor for
        SELECT DISTINCT fila_asiento 
        FROM tiquetes where vuelo_id = ArgIdVuelo;
    return filas_cursor;
end FILAS_OCUPADAS;
/
show error
------------------------------------------------------------------------------------------
create or replace function COLUMNASS_OCUPADAS_X_ASIENTO(ArgNumFila in VARCHAR2,ArgIdVuelo in VARCHAR2)
return TYPES.ref_cursor
as
columnas_cursor TYPES.ref_cursor;
begin
	open columnas_cursor for
         SELECT DISTINCT columna_asiento FROM Tiquetes --El distinct no hace falta, pero POR SI ACASO!
         WHERE fila_asiento = ArgNumFila and 
         vuelo_id = ArgIdVuelo;
    return columnas_cursor;
end COLUMNASS_OCUPADAS_X_ASIENTO;
/
show error
------------------------------------------------------------------------------------------

-- 0 admin / 1 user
--execute INSERCION_USUARIO('12345678','admin','David','Cordero Aguilar','David@gmial.com',sysdate,'Heredia','8888888','78787878',0);

--execute UPDATE_USUARIO('12345678','admin','David','Cordero Aguilar','lol@gmial.com',sysdate,'San Carlos','88888888','77777777',1);


variable x refcursor
exec :x:= GET_USUARIO('12');
print x;

variable x refcursor
exec :x:= LISTAR_USUARIO;
print x;


-------------------------------------------INSERT-----------------------------------------
INSERT INTO "SYSTEM"."USUARIOS" (ID, CONTRASENA, NOMBRE, APELLIDOS, CORREO,FECHA_NACIMIENTO, DIRECCION, TELEFONO_TRABAJO, CELULAR, ROL) VALUES ('12', '1234', 'davi', 'dda', 'dad@gmail.com', '21/04/21', 'La fortuna', '8656235', '23423232', '1');
INSERT INTO "SYSTEM"."USUARIOS" (ID, CONTRASENA, NOMBRE, APELLIDOS, CORREO,FECHA_NACIMIENTO, DIRECCION, TELEFONO_TRABAJO, CELULAR, ROL) VALUES ('13', '1234', 'david', 'davidCJ', 'david@gmail.com', '10/11/21', 'Heredia', '87656543', '45454545', '0');

INSERT INTO "SYSTEM"."CIUDAD" (ID, NOMBRE) VALUES ('72', 'Alajuela');
INSERT INTO "SYSTEM"."CIUDAD" (ID, NOMBRE) VALUES ('42', 'Pedregal');

INSERT INTO "SYSTEM"."HORARIOS" (ID, DIA_SEMANA, HORA_LLEGADA) VALUES ('1', 'Lunes', 10);
INSERT INTO "SYSTEM"."HORARIOS" (ID, DIA_SEMANA, HORA_LLEGADA) VALUES ('2', 'Martes', 8);

INSERT INTO "SYSTEM"."RUTAS" (ID, HORARIO_ID, CIUDAD_ORIGEN, CIUDAD_DESTINO, PRECIO, PORCENTAJE_DESCUENTO) VALUES ('1', '1', '72', '42', '15000', '0');

INSERT INTO "SYSTEM"."AVIONES" (ID, TIPO, CAPACIDAD, ANIO, MARCA, ASIENTOS_FILA, CANTIDAD_FILAS) VALUES ('1', 'carga', '120', '2020', 'David''s', '6', '20');

INSERT INTO "SYSTEM"."VUELOS" (ID, MODALIDAD, DURACION, RUTA_ID, AVION_ID, FECHA) VALUES ('2', '1', '1', '1', '1', TO_DATE('2020-12-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO "SYSTEM"."RUTAS" (ID, HORARIO_ID, CIUDAD_ORIGEN, CIUDAD_DESTINO, PRECIO, PORCENTAJE_DESCUENTO) VALUES ('3', '1', '72', '42', '2000', '20');

INSERT INTO "SYSTEM"."VUELOS" (ID, MODALIDAD, DURACION, RUTA_ID, AVION_ID, FECHA) VALUES ('3', '1', '1', '3', '1', TO_DATE('2021-04-14 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));

select Valida_Usuario('12','1234') from dual;

variable x refcursor
exec :x:= Valida_Usuario('12','1234');
print x;

exec INSERCION_CIUDAD('Miami- US');
exec INSERCION_CIUDAD('New York - US');
exec INSERCION_CIUDAD('Guanacaste - Costa Rica');
exec INSERCION_CIUDAD('Alajuela - Costa Rica');
exec INSERCION_CIUDAD('Buenos Aires - Argentina Rica');
exec INSERCION_CIUDAD('Ciudad de México - México');
exec INSERCION_CIUDAD('PanamaCity - Panama');




-- version 0.5