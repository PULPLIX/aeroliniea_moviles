
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
import logic.Horario;
import oracle.jdbc.OracleTypes;


public class ServicioHorario extends Servicio {
    
    private static final String INSERCION_HORARIO = "{call INSERCION_HORARIO(?,?)}";
    private static final String UPDATE_HORARIO = "{call UPDATE_HORARIO(?,?,?)}";
    private static final String GET_HORARIO = "{?=call GET_HORARIO(?)}";
    private static final String LISTAR_HORARIO = "{?=call LISTAR_HORARIO()}";
    private static final String DELETE_HORARIO = "{call DELETE_HORARIO(?)}";
    
    private static  ServicioHorario serviceHorario;
    
    
    private ServicioHorario(){
        
    }
    
    public static ServicioHorario getSingletonInstance() throws GeneralException{
        if(serviceHorario == null){
            serviceHorario = new ServicioHorario();
        }
        
        return serviceHorario;
        
    }

 
    public void insercionHorario(Horario newHorario) throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(INSERCION_HORARIO);
            toDo.setString(1, newHorario.getDiaSemana());            
            toDo.setInt(2, newHorario.getHoraLlegada());
            
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
    
    public void updateHorario(Horario newHorario) throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        PreparedStatement toDo = null;
        try {
            toDo = conexion.prepareCall(UPDATE_HORARIO);
            toDo.setInt(1, newHorario.getId());
            toDo.setString(2, newHorario.getDiaSemana());            
            toDo.setInt(3, newHorario.getHoraLlegada());

            
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

    
    

    public Horario getHorario(int id) throws GeneralException, DbException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se pudo localizar el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        
        ResultSet rs = null;
        Horario Horario = null;
        CallableStatement toDo = null;
        
        try {
            toDo = conexion.prepareCall(GET_HORARIO);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.setInt(2, id);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);
            
            if (rs.next()) {                
                Horario = new Horario(rs.getInt("id"), rs.getString("dia_semana"), 
                    rs.getInt("hora_llegada") );               
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
        
        if (Horario == null) {
            throw new DbException("El curso no existe");
        }
        
        return Horario;
    }
    
    public Collection listar_horario() throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GeneralException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Horario Horario = null;
        CallableStatement toDo = null;
        
        try {
            toDo = conexion.prepareCall(LISTAR_HORARIO);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);
            
            while (rs.next()) {
                Horario = new Horario(rs.getInt("id"), rs.getString("dia_semana"), 
                    rs.getInt("hora_llegada") ); 
                coleccion.add(Horario);
                
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



    
    public void deleteHorario(int id) throws GeneralException, DbException {
        
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        
        PreparedStatement toDo = null;
        try {
            toDo = conexion.prepareStatement(DELETE_HORARIO);
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
