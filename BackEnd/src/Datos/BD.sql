host cls

drop table Usuario cascade constraint;
drop table Curso;
drop table Carrera;

set linesize 300;
set pagesize 300;


create table Carrera(
	codigoCarrera varchar2(10) NOT NULL,
	nombre 	varchar2(100) NOT NULL,
	titulo 	varchar2(100) NOT NULL,
	constraint PKCarrera primary key(codigoCarrera)
);


create table Curso(
	codigoCurso varchar2(10) NOT NULL,
	nombreCurso varchar2(100) NOT NULL,
	creditos NUMBER NOT NULL,
	horasSemanales NUMBER NOT NULL,
	nivel NUMBER NOT NULL,
	ciclo NUMBER  NOT NULL,
	codigoCarrera VARCHAR2(10) NOT NULL,
	constraint Curso_pk primary key(codigoCurso),
	constraint FKCursor foreign key (codigoCarrera) references Carrera (codigoCarrera)
);

create table Usuario(
    cedula VARCHAR2(10) NOT NULL,
    clave VARCHAR2(30) NOT NULL,
    rol NUMBER NOT NULL,
    CONSTRAINT PKUsuario PRIMARY KEY (cedula)
);
	
	
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
ArgCedula in VARCHAR2,
ArgClave in VARCHAR2, 
ArgRol in NUMBER ) as
begin
	insert into Usuario(cedula,clave,rol) values (ArgCedula,ArgClave,ArgRol);	
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
create or replace procedure DELETE_USUARIO(ArgCEDULA in VARCHAR2) as
begin
	delete Usuario where cedula = ArgCEDULA;	
	commit;
end DELETE_USUARIO;
/
show error

------------------------------------------------------------------------------------------





------------------------------------------------------------------------------------------
create or replace function Valida_Usuario(ArgCedula in VARCHAR2, ArgClave in VARCHAR2)
return TYPES.ref_cursor
as
usuario_cursor TYPES.ref_cursor;
begin
	open usuario_cursor for
	select cedula,rol from Usuario where cedula = ArgCedula and clave=ArgClave;	
	return usuario_cursor;
end Valida_Usuario;
/
show error
------------------------------------------------------------------------------------------







------------------------------------------------------------------------------------------
create or replace function GET_Usuario(ArgCedula in VARCHAR2)
return TYPES.ref_cursor
as
usuario_cursor TYPES.ref_cursor;
begin
	open usuario_cursor for
	select cedula,rol from Usuario where cedula = ArgCedula;	
	return usuario_cursor;
end GET_Usuario;
/
show error
------------------------------------------------------------------------------------------




 
------------------------------------------------------------------------------------------
create or replace procedure INSERCION_CARRERA(
ArgCodigo in VARCHAR2,
ArgNombre in VARCHAR2, 
ArgTitulo in VARCHAR2) as
begin
	insert into Carrera(codigoCarrera,nombre,titulo) values (ArgCodigo,ArgNombre,ArgTitulo);	
	commit;
end INSERCION_CARRERA;
/
show error
------------------------------------------------------------------------------------------




------------------------------------------------------------------------------------------
create or replace procedure UPDATE_CARRERA(
ArgCodigo in VARCHAR2,
ArgNombre in VARCHAR2, 
ArgTitulo in VARCHAR2) as
begin
	update Carrera set nombre = ArgNombre, titulo=ArgTitulo where codigoCarrera = ArgCodigo;
	commit;
end UPDATE_CARRERA;
/
show error
------------------------------------------------------------------------------------------




------------------------------------------------------------------------------------------
create or replace procedure DELETE_CARRERA(ArgCodigo in VARCHAR2) as
begin
	delete Carrera where codigoCarrera = ArgCodigo;	
	commit;
end DELETE_CARRERA;
/
show error

------------------------------------------------------------------------------------------




------------------------------------------------------------------------------------------
create or replace function GET_CARRERA(ArgCodigo in VARCHAR2)
return TYPES.ref_cursor
as
carrera_cursor TYPES.ref_cursor;
begin
	open carrera_cursor for
	select codigoCarrera,nombre,titulo from Carrera where codigoCarrera = ArgCodigo;	
	return carrera_cursor;
end GET_CARRERA;
/
show error
------------------------------------------------------------------------------------------




------------------------------------------------------------------------------------------
create or replace function LISTAR_CARRERA 
return TYPES.ref_cursor
as
carrera_cursor TYPES.ref_cursor;
begin
	open carrera_cursor for
	select codigoCarrera,nombre,titulo from Carrera;
	return carrera_cursor;
end LISTAR_CARRERA;
/
show error
------------------------------------------------------------------------------------------





------------------------------------------------------------------------------------------
create or replace procedure INSERCION_CURSO(
ArgCodigo 	in VARCHAR2,
ArgNombre 	in VARCHAR2, 
ArgCreditos in NUMBER,
ArgHorasSem in NUMBER,
ArgNivel	in NUMBER,
ArgCiclo 	in NUMBER,
ArgCodigoCarrera in VARCHAR2
) is
begin
	insert into Curso(codigoCurso,nombreCurso,creditos,horasSemanales,nivel,ciclo,codigoCarrera) 
	values (ArgCodigo,ArgNombre,ArgCreditos,ArgHorasSem,ArgNivel,ArgCiclo,ArgCodigoCarrera);

	commit;
end INSERCION_CURSO;
/
show error
------------------------------------------------------------------------------------------






------------------------------------------------------------------------------------------
create or replace procedure UPDATE_CURSO(
ArgCodigoCurso in VARCHAR2,
ArgNombre in VARCHAR2, 
ArgCreditos in VARCHAR2,
ArgHorasSem in NUMBER,
ArgNivel in NUMBER,
ArgCiclo  in NUMBER,
ArgCodCarrera in VARCHAR2
) is
begin
	UPDATE Curso SET nombreCurso = ArgNombre, creditos=ArgCreditos, horasSemanales=ArgHorasSem,
	nivel=ArgNivel, ciclo=ArgCiclo, codigoCarrera=ArgCodCarrera WHERE codigoCurso=ArgCodigoCurso;	
	
	commit;
end UPDATE_CURSO;
/
show error
------------------------------------------------------------------------------------------






------------------------------------------------------------------------------------------
create or replace procedure DELETE_CURSO(ArgCodigo in VARCHAR2) is
begin
	delete Curso where codigoCurso = ArgCodigo;	
	
	commit;
end DELETE_CURSO;
/
show error
------------------------------------------------------------------------------------------





------------------------------------------------------------------------------------------
create or replace function GET_CURSO(ArgCodigo in varchar2)
return TYPES.ref_cursor
as
curso_cursor TYPES.ref_cursor;
begin
	open curso_cursor for
	select codigoCurso,nombreCurso,creditos,horasSemanales,nivel,ciclo,codigoCarrera from Curso where codigoCurso = ArgCodigo;	
	return curso_cursor;
end GET_CURSO;
/
show error
------------------------------------------------------------------------------------------





------------------------------------------------------------------------------------------
create or replace function LISTAR_CURSO
return TYPES.ref_cursor
as
curso_cursor TYPES.ref_cursor;
begin
	open curso_cursor for
	select codigoCurso,nombreCurso,creditos,horasSemanales,nivel,ciclo,codigoCarrera from Curso;
	return curso_cursor;
end LISTAR_CURSO;
/
show error
------------------------------------------------------------------------------------------



-- delete from carrera;
execute INSERCION_CARRERA('EIF','Ing. Sistemas.','Bachiller');
execute INSERCION_CARRERA ('FSF', 'Filosofia', 'Licenciatura');
execute INSERCION_CARRERA ('EPC', 'Preescolar', 'Bachiller');
-- commit;

-- variable x refcursor
-- exec :x:= CONSULTAR_CARRERA('EIF');
-- print x;

-- variable x refcursor
-- exec :x:= LISTA_CARRERA;
-- print x;

-- PROMPT =================================================================

-- delete from curso;
execute INSERCION_CURSO('EIF213','Programacion 4',9,9,2,4,'EIF');
execute INSERCION_CURSO('EIF211','Inge I',4,7,3,3,'EIF');
execute INSERCION_CURSO('EIF212','Aplicaciones moviles',5,4,3,2,'EIF');
execute INSERCION_CURSO('FSF45','Filosofia',4,3,2,1,'FSF');
execute INSERCION_CURSO('FSF32','Filosofia',4,6,5,4,'FSF');


execute INSERCION_USUARIO('admin','1234',0);
execute INSERCION_USUARIO('root','root',0);
execute INSERCION_USUARIO('207970180','1234',0);

-- variable x refcursor
-- exec :x:= CONSULTAR_CURSO('EIF212');
-- print x;

-- variable x refcursor
-- exec :x:= LISTA_CURSO;
-- print x;
