package DataAccess;

import Exceptions.DbException;
import Exceptions.GeneralException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import logic.Carrera;
import oracle.jdbc.OracleTypes;

public class ServicioCarrera extends Servicio {

    private static final String INSERCION_CARRERA = "{call INSERCION_CARRERA(?,?,?)}";
    private static final String UPDATE_CARRERA = "{call UPDATE_CARRERA(?,?,?)}";
    private static final String GET_CARRERA = "{?=call GET_CARRERA(?)}";
    private static final String LISTAR_CARRERA = "{?=call LISTAR_CARRERA()}";
    private static final String DELETE_CARRERA = "{call DELETE_CARRERA(?)}";
    
    private static  ServicioCarrera serviceCarrera;
    
    
    private ServicioCarrera(){
        
    }
    
    public static ServicioCarrera getSingletonInstance() throws GeneralException{
        if(serviceCarrera == null){
            serviceCarrera = new ServicioCarrera();
        }
        
        return serviceCarrera;
        
    }


    public void insercionCarrera(Carrera newCarrera) throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(INSERCION_CARRERA);
            toDo.setString(1, newCarrera.getCodigo());
            toDo.setString(2, newCarrera.getNombre());
            toDo.setString(3, newCarrera.getTituloCarrera());

            boolean resultado = toDo.execute();
            if (resultado == true) {
                throw new DbException("No se pudo agregar la carrera");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DbException("El identificador de carrera ya está en uso");
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new GeneralException("Ha ocurrido un error, vuelva a intentar...");
        } finally {
            try {
                if (toDo != null) {
                    toDo.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GeneralException("Datos invalidos o nulos");
            }
        }
    }

    public void updateCarrera(Carrera newCarrera) throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        PreparedStatement toDo = null;
        try {
            toDo = conexion.prepareCall(UPDATE_CARRERA);
            toDo.setString(1, newCarrera.getCodigo());
            toDo.setString(2, newCarrera.getNombre());
            toDo.setString(3, newCarrera.getTituloCarrera());

            int resultado = toDo.executeUpdate();

            if (resultado == 0) {
                throw new DbException("La actualizacion de carrera no se realizo");
            } else {
               // System.out.println("\nLa actualizacion de carrera se realizo con exito!");
            }
        } catch (SQLException e) {
            throw new GeneralException("Sentencia no valida");
        } finally {
            try {
                if (toDo != null) {
                    toDo.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GeneralException("Datos invalidos o nulos");
            }
        }
    }
    
    
    

    public Carrera getCarrera(String id) throws GeneralException, DbException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se pudo localizar el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        ResultSet rs = null;
        Carrera carrera = null;
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(GET_CARRERA);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.setString(2, id);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);

            if (rs.next()) {
                carrera = new Carrera(rs.getString("codigoCarrera"), rs.getString("nombre"), rs.getString("titulo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();

            throw new GeneralException("Sentencia no valida");

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (toDo != null) {
                    toDo.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GeneralException("Datos invalidos o nulos");
            }
        }

        if (carrera == null) {
            throw new DbException("La carrera no existe");
        }

        return carrera;
    }

    
    
    public Collection listar_carrera() throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GeneralException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Carrera carrera = null;
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(LISTAR_CARRERA);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);

            while (rs.next()) {
                carrera = new Carrera(rs.getString("codigoCarrera"), rs.getString("nombre"), rs.getString("titulo"));
                coleccion.add(carrera);

            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new GeneralException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (toDo != null) {
                    toDo.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GeneralException("Datos invalidos o nulos");
            }
        }

        if (coleccion.isEmpty()) {
            throw new DbException("No hay datos");
        }

        return coleccion;
    }
    
    public void deleteCarrera(String id) throws GeneralException, DbException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        PreparedStatement toDo = null;
        try {
            toDo = conexion.prepareStatement(DELETE_CARRERA);
            toDo.setString(1, id);

            int resultado = toDo.executeUpdate();

            if (resultado == 0) {
                throw new DbException("No se ha realizado la eliminacion");
            } else {
                //System.out.println("\nSe ha eliminado exitosamente!");
            }
        }catch (SQLIntegrityConstraintViolationException e) {
            throw new DbException("No se puede eliminar la carrera porque tiene cursos asociados");
        }  
        catch (SQLException e) {
            throw new GeneralException("Sentencia no valida");
        } finally {
            try {
                if (toDo != null) {
                    toDo.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GeneralException("Datos invalidos o nulos");
            }
        }
    }
}
