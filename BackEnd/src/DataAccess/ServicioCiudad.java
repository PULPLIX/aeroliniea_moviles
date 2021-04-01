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
import logic.Ciudad;
import oracle.jdbc.OracleTypes;

public class ServicioCiudad extends Servicio {

    private static final String INSERCION_CIUDAD = "{call INSERCION_CIUDAD(?)}";
    private static final String UPDATE_CIUDAD = "{call UPDATE_CIUDAD(?,?)}";
    private static final String GET_CIUDAD = "{?=call GET_CIUDAD(?)}";
    private static final String LISTAR_CIUDAD = "{?=call LISTAR_CIUDAD()}";
    private static final String DELETE_CIUDAD = "{call DELETE_CIUDAD(?)}";
    
    private static  ServicioCiudad ciudadInstance;
    
    
    private ServicioCiudad(){
        
    }
    
    public static ServicioCiudad getSingletonInstance() throws GeneralException{
        if(ciudadInstance == null){
            ciudadInstance = new ServicioCiudad();
        }
        
        return ciudadInstance;
        
    }


    public void insercionCiudad(Ciudad newCiudad) throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(INSERCION_CIUDAD);
            toDo.setString(1, newCiudad.getNombre());

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

    public void updateCiudad(Ciudad newCiudad) throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        PreparedStatement toDo = null;
        try {
            toDo = conexion.prepareCall(UPDATE_CIUDAD);
            toDo.setInt(1, newCiudad.getId());
            toDo.setString(2, newCiudad.getNombre());

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
    
    
    

    public Ciudad getCiudad(int id) throws GeneralException, DbException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se pudo localizar el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        ResultSet rs = null;
        Ciudad carrera = null;
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(GET_CIUDAD);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.setInt(1, id);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);

            if (rs.next()) {
                carrera = new Ciudad(rs.getInt("id"), rs.getString("nombre"));
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

    
    
    public Collection listar_ciudades() throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GeneralException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Ciudad ciudad = null;
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(LISTAR_CIUDAD);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);

            while (rs.next()) {
                ciudad = new Ciudad(rs.getInt("id"), rs.getString("nombre"));
                coleccion.add(ciudad);

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
    
    public void deleteCiudad(int id) throws GeneralException, DbException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        PreparedStatement toDo = null;
        try {
            toDo = conexion.prepareStatement(DELETE_CIUDAD);
            toDo.setInt(1, id);

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
