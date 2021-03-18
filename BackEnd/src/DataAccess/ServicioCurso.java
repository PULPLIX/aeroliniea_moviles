
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
import logic.Curso;
import oracle.jdbc.OracleTypes;


public class ServicioCurso extends Servicio {
    
    private static final String INSERCION_CURSO = "{call INSERCION_CURSO(?,?,?,?,?,?,?)}";
    private static final String UPDATE_CURSO = "{call UPDATE_CURSO(?,?,?,?,?,?,?)}";
    private static final String GET_CURSO = "{?=call GET_CURSO(?)}";
    private static final String LISTAR_CURSO = "{?=call LISTAR_CURSO()}";
    private static final String DELETE_CURSO = "{call DELETE_CURSO(?)}";
    
    private static  ServicioCurso serviceCurse;
    
    
    private ServicioCurso(){
        
    }
    
    public static ServicioCurso getSingletonInstance() throws GeneralException{
        if(serviceCurse == null){
            serviceCurse = new ServicioCurso();
        }
        
        return serviceCurse;
        
    }

 
    public void insercionCurso(Curso newCurse) throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(INSERCION_CURSO);
            toDo.setString(1, newCurse.getCodigoCurso());
            toDo.setString(2, newCurse.getNombre());            
            toDo.setInt(3, newCurse.getCreditos());
            toDo.setInt(4, newCurse.getHorasSemanales());
            toDo.setInt(5, newCurse.getNivel());
            toDo.setInt(6, newCurse.getCiclo());
            toDo.setString(7, newCurse.getCodigoCarrera());
            
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
    
    public void updateCurso(Curso newCurse) throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        PreparedStatement toDo = null;
        try {
            toDo = conexion.prepareCall(UPDATE_CURSO);
            toDo.setString(1, newCurse.getCodigoCurso());
            toDo.setString(2, newCurse.getNombre());
            toDo.setInt(3, newCurse.getCreditos());
            toDo.setInt(4, newCurse.getHorasSemanales());
            toDo.setInt(5, newCurse.getNivel());
            toDo.setInt(6, newCurse.getCiclo());
            toDo.setString(7, newCurse.getCodigoCarrera());            

            
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

    
    

    public Curso getCurso(String id) throws GeneralException, DbException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se pudo localizar el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        
        ResultSet rs = null;
        Curso curse = null;
        CallableStatement toDo = null;
        
        try {
            toDo = conexion.prepareCall(GET_CURSO);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.setString(2, id);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);
            
            if (rs.next()) {                
                curse = new Curso(rs.getString("codigoCurso"), rs.getString("nombreCurso"), 
                    rs.getString("codigoCarrera"), rs.getInt("nivel"),
                    rs.getInt("ciclo"),rs.getInt("horasSemanales"),
                    rs.getInt("creditos") );                
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
        
        if (curse == null) {
            throw new DbException("El curso no existe");
        }
        
        return curse;
    }

    
    
    
    public Collection listar_curso() throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GeneralException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Curso curse = null;
        CallableStatement toDo = null;
        
        try {
            toDo = conexion.prepareCall(LISTAR_CURSO);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);
            
            while (rs.next()) {
                curse = new Curso(rs.getString("codigoCurso"), rs.getString("nombreCurso"), 
                    rs.getString("codigoCarrera"), rs.getInt("nivel"),
                    rs.getInt("ciclo"),rs.getInt("horasSemanales"),
                    rs.getInt("creditos") );  
                coleccion.add(curse);
                
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



    
    public void deleteCurso(String id) throws GeneralException, DbException {
        
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        
        PreparedStatement toDo = null;
        try {
            toDo = conexion.prepareStatement(DELETE_CURSO);
            toDo.setString(1, id);

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
