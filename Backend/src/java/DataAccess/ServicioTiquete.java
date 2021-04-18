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
import logic.Ciudad;
import logic.Horario;
import logic.Ruta;
import logic.Tiquete;
import logic.Usuario;
import logic.Vuelo;
import oracle.jdbc.OracleTypes;

public class ServicioTiquete extends Servicio {

    private static final String INSERCION_TIQUETE = "{call INSERCION_TIQUETE(?,?,?,?,?,?)}";
    private static final String UPDATE_TIQUETE = "{call UPDATE_TIQUETE(?,?,?,?,?,?,?)}";
    private static final String GET_TIQUETE = "{?=call GET_TIQUETE(?)}";
    private static final String LISTAR_TIQUETE = "{?=call LISTAR_TIQUETES()}";
    private static final String DELETE_TIQUETE = "{call DELETE_TIQUETE(?)}";
    private static final String HISTORIAL_TIQUETES = "{?=call HISTORIAL_TIQUETE(?)}";

    private static ServicioTiquete serviceTiquete;

    private ServicioTiquete() {

    }

    public static ServicioTiquete getSingletonInstance() throws GeneralException {
        if (serviceTiquete == null) {
            serviceTiquete = new ServicioTiquete();
        }

        return serviceTiquete;

    }

    public void insercionTiquete(Tiquete newTiquete) throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(INSERCION_TIQUETE);
            toDo.setString(1, newTiquete.getUsuarioId().getId());
            toDo.setInt(2, newTiquete.getVueloId().getId());
            toDo.setDouble(3, newTiquete.getPrecioFinal());
            toDo.setInt(4, newTiquete.getFilaAsisento());
            toDo.setInt(5, newTiquete.getColumnaAsiento());
            toDo.setString(6, newTiquete.getFormaPago());

            boolean resultado = toDo.execute();
            if (resultado == true) {
                throw new DbException("No se realizo la insercion del tiquete");
            }

        } catch (SQLException e) {
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

    public void updateTiquete(Tiquete tiquete) throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        PreparedStatement toDo = null;
        try {
            toDo = conexion.prepareCall(UPDATE_TIQUETE);
            toDo.setString(1, tiquete.getUsuarioId().getId());
            toDo.setInt(2, tiquete.getVueloId().getId());
            toDo.setDouble(3, tiquete.getPrecioFinal());
            toDo.setInt(4, tiquete.getFilaAsisento());
            toDo.setInt(5, tiquete.getColumnaAsiento());
            toDo.setString(6, tiquete.getFormaPago());

            int resultado = toDo.executeUpdate();

            if (resultado == 0) {
                throw new DbException("La actualizacion del curso no se realizo");
            } else {
                //La actualizacion se realizo con exito!
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DbException("El identificador de carrera no existe!");
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

    public Tiquete getTiquete(int id) throws GeneralException, DbException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se pudo localizar el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        ResultSet rs = null;
        Tiquete Tiquete = null;
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(GET_TIQUETE);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.setInt(2, id);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);

            if (rs.next()) {
                Usuario usuarioTem = ServicioUsuario.getSingletonInstance().getUsuario(rs.getString("usuario_id"));
                Vuelo vueloTem = ServicioVuelo.getSingletonInstance().getVuelo(rs.getInt("vuelo_id"));
                Tiquete = new Tiquete(rs.getInt("id"), usuarioTem, vueloTem,
                        rs.getDouble("precio_final"), rs.getInt("fila_asisento"),
                        rs.getInt("columna_asiento"), rs.getString("forma_pago"));
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

        if (Tiquete == null) {
            throw new DbException("El curso no existe");
        }

        return Tiquete;
    }

    public Collection listar_tiquete() throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GeneralException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Tiquete Tiquete = null;
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(LISTAR_TIQUETE);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);

            while (rs.next()) {
                Usuario usuarioTem = ServicioUsuario.getSingletonInstance().getUsuario(rs.getString("usuario_id"));
                Vuelo vueloTem = ServicioVuelo.getSingletonInstance().getVuelo(rs.getInt("vuelo_id"));

                Tiquete = new Tiquete(rs.getInt("id"), usuarioTem, vueloTem,
                        rs.getDouble("precio_final"), rs.getInt("fila_asiento"),
                        rs.getInt("columna_asiento"), rs.getString("forma_pago"));
                coleccion.add(Tiquete);

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

    public ArrayList getHistorialTiquetes(String id) throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GeneralException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Tiquete Tiquete = null;
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(HISTORIAL_TIQUETES);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.setString(2, id);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);
            Usuario usuarioTem = ServicioUsuario.getSingletonInstance().getUsuario(id);

            while (rs.next()) {
                Vuelo vueloTem = ServicioVuelo.getSingletonInstance().getVuelo(rs.getInt("vuelo_id"));

                Tiquete = new Tiquete(rs.getInt("id"), usuarioTem, vueloTem,
                        rs.getDouble("precio_final"), rs.getInt("fila_asiento"),
                        rs.getInt("columna_asiento"), rs.getString("forma_pago"));
                coleccion.add(Tiquete);

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

    public void deleteTiquete(int id) throws GeneralException, DbException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        PreparedStatement toDo = null;
        try {
            toDo = conexion.prepareStatement(DELETE_TIQUETE);
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
