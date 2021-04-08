
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
import logic.Avion;
import oracle.jdbc.OracleTypes;


public class ServicioAviones extends Servicio {
    
    private static final String INSERCION_AVIONES   = "{call INSERCION_AVIONES(?,?,?,?,?,?)}";
    private static final String UPDATE_AVIONES  = "{call UPDATE_AVIONES(?,?,?,?,?,?,?)}";
    private static final String GET_AVIONES = "{?=call GET_AVIONES(?)}";
    private static final String LISTAR_AVIONES  = "{?=call LISTAR_AVIONES()}";
    private static final String DELETE_AVIONES  = "{call DELETE_AVIONES(?)}";
    
    private static  ServicioAviones serviceAviones;
    
    
    private ServicioAviones(){
        
    }
    
    public static ServicioAviones getSingletonInstance() throws GeneralException{
        if(serviceAviones == null){
            serviceAviones = new ServicioAviones();
        }
        
        return serviceAviones;
        
    }

 
    public void insercionAviones(Avion newAviones) throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        CallableStatement toDo = null;
        try {
            toDo = conexion.prepareCall(INSERCION_AVIONES);
            toDo.setString(1, newAviones.getTipo());            
            toDo.setInt(2, newAviones.getCapacidad());
            toDo.setInt(3, newAviones.getAnio());
            toDo.setString(4, newAviones.getMarca());
            toDo.setInt(5, newAviones.getAsientosFila());
            toDo.setInt(6, newAviones.getCantidadFilas());
            
            boolean resultado = toDo.execute();
            if (resultado == true) {
                throw new DbException("No se realizo la insercion del curso");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DbException("El identificador de curso ya está en uso o el codigo de carrera no existe");
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
    
    public void updateAvion(Avion newAviones) throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        PreparedStatement toDo = null;
        try {
            toDo = conexion.prepareCall(UPDATE_AVIONES);
            toDo.setString(1, String.valueOf(newAviones.getId()));
            toDo.setString(2, newAviones.getTipo());            
            toDo.setInt(3, newAviones.getCapacidad());
            toDo.setInt(4, newAviones.getAnio());
            toDo.setString(5, newAviones.getMarca());
            toDo.setInt(6, newAviones.getAsientosFila());
            toDo.setInt(7, newAviones.getCantidadFilas());           

            
            int resultado = toDo.executeUpdate();

            if (resultado == 0) {
                throw new DbException("La actualizacion del curso no se realizo");
            } else {
                  //La actualizacion se realizo con exito!
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DbException("El identificador de carrera no existe!");
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

    
    

    public Avion getAvion(int id) throws GeneralException, DbException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se pudo localizar el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        
        ResultSet rs = null;
        Avion Avion = null;
        CallableStatement toDo = null;
        
        try {
            toDo = conexion.prepareCall(GET_AVIONES);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.setInt(2, id);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);
            
            if (rs.next()) {                
                Avion = new Avion(rs.getInt("id"), rs.getString("tipo"), 
                    rs.getInt("capacidad"), rs.getInt("anio"),
                    rs.getString("marca"),rs.getInt("asientos_fila"),
                    rs.getInt("cantidad_filas") );                
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
        
        if (Avion == null) {
            throw new DbException("El avión no existe");
        }
        
        return Avion;
    }

    
    
    
    public Collection listar_aviones() throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GeneralException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Avion avion = null;
        CallableStatement toDo = null;
        
        try {
            toDo = conexion.prepareCall(LISTAR_AVIONES);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);
            
            while (rs.next()) {
                avion = new Avion(rs.getInt("id"), rs.getString("tipo"), 
                    rs.getInt("capacidad"), rs.getInt("anio"),
                    rs.getString("marca"),rs.getInt("asientos_fila"),
                    rs.getInt("cantidad_filas") );
                coleccion.add(avion);
                
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
        
//        if (coleccion.isEmpty()) {
//            throw new DbException("No hay datos");
//        }
        
        return coleccion;
    }



    
    public void deleteAvion(int id) throws GeneralException, DbException {
        
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        
        PreparedStatement toDo = null;
        try {
            toDo = conexion.prepareStatement(DELETE_AVIONES);
            toDo.setInt(1, id);

            int resultado = toDo.executeUpdate();

            if (resultado == 0) {
                throw new DbException("No se ha realizado la eliminacion");
            } else {
                //Se ha eliminado exitosamente!
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
}
