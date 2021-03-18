
package DataAccess;

import Exceptions.DbException;
import Exceptions.GeneralException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logic.Usuario;
import oracle.jdbc.OracleTypes;


public class ServicioUsuario extends Servicio {
    
    private static final String INSERCION_USUARIO = "{call INSERCION_USUARIO(?,?,?)}";
    private static final String UPDATE_USUARIO = "{call UPDATE_USUARIO(?,?,?)}";
    private static final String GET_USUARIO = "{?=call GET_USUARIO(?)}";
    private static  final String VALIDA_USUARIO ="{?=call Valida_Usuario(?,?)}";
    private static final String DELETE_USUARIO = "{call DELETE_USUARIO(?)}";
    //private static final String LISTAR_USUARIO = "{?=call LISTAR_USUARIO()}";
    
    private static  ServicioUsuario serviceUser;
    
    private ServicioUsuario(){
        
    }
    
    public static ServicioUsuario getSingletonInstance() throws GeneralException{
        if(serviceUser == null){
            serviceUser = new ServicioUsuario();
        }
        
        return serviceUser;
        
    }
    
    
    
    public void insercionUsuario(Usuario newUsuario) throws GeneralException, DbException {
        
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(INSERCION_USUARIO);
            toDo.setString(1, newUsuario.getCedula());
            toDo.setString(2, newUsuario.getClave());
            toDo.setInt(3, newUsuario.getRol());

            boolean resultado = toDo.execute();
            
            if (resultado == true) {
                throw new DbException("No se realizo la insercion");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new GeneralException("Llave repetida");
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
    
    
    
    
    public void updateUsuario(Usuario newUsuario) throws GeneralException, DbException {
        
        
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        PreparedStatement toDo = null;
        
        
        try {
            toDo = conexion.prepareCall(UPDATE_USUARIO);
            toDo.setString(1, newUsuario.getCedula());
            toDo.setString(2, newUsuario.getClave());
            toDo.setInt(3, newUsuario.getRol());

            int resultado = toDo.executeUpdate();

            if (resultado == 0) {
                throw new DbException("La actualizaciOn no se realizo");
            } else {
                System.out.println("\nLa actualizacion se realizo con exito!");
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
    
    
    
    
    public Usuario validaUsario(String id, String clave) throws GeneralException, DbException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se pudo localizar el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        ResultSet rs = null;
        Usuario user = null;
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(VALIDA_USUARIO);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.setString(2, id);
            toDo.setString(3,clave);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);

            if (rs.next()) {
                user = new Usuario(rs.getString("cedula"), "", rs.getInt("rol"));
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

        if (user == null) {
            throw new DbException("No hay datos");
        }

        return user;
    }
    
    
    
    
    
    public Usuario getUsuario(String id) throws GeneralException, DbException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se pudo localizar el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        ResultSet rs = null;
        Usuario user = null;
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(GET_USUARIO);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.setString(2, id);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);

            if (rs.next()) {
                user = new Usuario(rs.getString("cedula"), "", rs.getInt("rol"));
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

        if (user == null) {
            throw new DbException("No hay datos");
        }

        return user;
    }
    
   
    
    
    
    
    
    public void deleteUsuario(String id) throws GeneralException, DbException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        PreparedStatement toDo = null;
        try {
            toDo = conexion.prepareStatement(DELETE_USUARIO);
            toDo.setString(1, id);

            int resultado = toDo.executeUpdate();

            if (resultado == 0) {
                throw new DbException("No se ha realizado la eliminacion");
            } else {
                System.out.println("\nSe ha eliminado exitosamente!");
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
