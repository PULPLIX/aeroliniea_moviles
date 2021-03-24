
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
import logic.Ruta;
import logic.Vuelo;
import oracle.jdbc.OracleTypes;


public class ServicioVuelo extends Servicio {
    
    private static final String INSERCION_VUELO = "{call INSERCION_VUELO(?,?,?,?,?)}";
    private static final String UPDATE_VUELO = "{call UPDATE_VUELO(?,?,?,?,?,?)}";
    private static final String GET_VUELO = "{?=call GET_VUELO(?)}";
    private static final String LISTAR_VUELO = "{?=call LISTAR_VUELO()}";
    private static final String DELETE_VUELO = "{call DELETE_VUELO(?)}";
    
    private static  ServicioVuelo serviceVuelo;
    
    private ServicioVuelo(){
        
    }
    
    public static ServicioVuelo getSingletonInstance() throws GeneralException{
        if(serviceVuelo == null){
            serviceVuelo = new ServicioVuelo();
        }
        
        return serviceVuelo;
        
    }

 
    public void insercionVuelo(Vuelo newVuelo) throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(INSERCION_VUELO);
            toDo.setInt(1, newVuelo.getModalidad());            
            toDo.setInt(2, newVuelo.getDuracion());
            toDo.setInt(3, newVuelo.getRutaId().getId())  ;
            toDo.setInt(4, newVuelo.getAvionId().getId());
            toDo.setString(5, newVuelo.getFecha().toString());
            
            
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
    
    public void updateVuelo(Vuelo newVuelo) throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        PreparedStatement toDo = null;
        try {
            toDo = conexion.prepareCall(UPDATE_VUELO);
            toDo.setInt(1, newVuelo.getId());  
            toDo.setInt(2, newVuelo.getModalidad());            
            toDo.setInt(3, newVuelo.getDuracion());
            toDo.setInt(4, newVuelo.getRutaId().getId())  ;
            toDo.setInt(5, newVuelo.getAvionId().getId());
            toDo.setString(6, newVuelo.getFecha().toString());


            
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

    
    

    public Vuelo getVuelo(int id) throws GeneralException, DbException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se pudo localizar el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        
        ResultSet rs = null;
        Vuelo Vuelo = null;
        CallableStatement toDo = null;
        
        try {
            toDo = conexion.prepareCall(GET_VUELO);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.setInt(2, id);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);
            
            if (rs.next()) {           
                Ruta ruta = ServicioRutas.getSingletonInstance().getRuta(rs.getInt("ruta_id"));

                Avion avion = ServicioAviones.getSingletonInstance().getAvion(rs.getInt("avion_id"));

                Vuelo = new Vuelo(rs.getInt("id"), rs.getInt("modalidad"),
                             rs.getInt("duracion"),rs.getDate("fecha"),
                            avion,ruta);               
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
        
        if (Vuelo == null) {
            throw new DbException("El curso no existe");
        }
        
        return Vuelo;
    }
    
    public Collection listar_vuelo() throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GeneralException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Vuelo Vuelo = null;
        CallableStatement toDo = null;
        
        try {
            toDo = conexion.prepareCall(LISTAR_VUELO);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);
            
            while (rs.next()) {
                Ruta ruta = ServicioRutas.getSingletonInstance().getRuta(rs.getInt("ruta_id"));

                Avion avion = ServicioAviones.getSingletonInstance().getAvion(rs.getInt("avion_id"));

                Vuelo = new Vuelo(rs.getInt("id"), rs.getInt("modalidad"),
                             rs.getInt("duracion"),rs.getDate("fecha"),
                            avion,ruta);  
                coleccion.add(Vuelo);
                
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



    
    public void deleteVuelo(int id) throws GeneralException, DbException {
        
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        
        PreparedStatement toDo = null;
        try {
            toDo = conexion.prepareStatement(DELETE_VUELO);
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

