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
import logic.Horario;
import logic.Ruta;
import oracle.jdbc.OracleTypes;

public class ServicioRutas extends Servicio {

    private static final String INSERCION_RUTAS = "{call INSERCION_RUTA(?,?,?,?,?)}";
    private static final String UPDATE_RUTAS = "{call UPDATE_RUTA(?,?,?,?,?,?)}";
    private static final String GET_RUTAS = "{?=call GET_RUTA(?)}";
    private static final String LISTAR_RUTAS = "{?=call LISTAR_RUTAS()}";
    private static final String DELETE_RUTAS = "{call DELETE_RUTA(?)}";

    private static ServicioRutas serviceRutas;

    private ServicioRutas() {

    }

    public static ServicioRutas getSingletonInstance() throws GeneralException {
        if (serviceRutas == null) {
            serviceRutas = new ServicioRutas();
        }

        return serviceRutas;

    }

    public void insercionRuta(Ruta newRutas) throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        CallableStatement toDo = null;
        try {
            toDo = conexion.prepareCall(INSERCION_RUTAS);
            toDo.setInt(1, newRutas.getHorarioId().getId());
            toDo.setInt(2, newRutas.getCiudadOrigen().getId());
            toDo.setInt(3, newRutas.getCiudadDestino().getId());
            toDo.setDouble(4, newRutas.getPrecio());
            toDo.setDouble(5, newRutas.getPorcentajeDescuento());

            boolean resultado = toDo.execute();
            if (resultado == true) {
                throw new DbException("No se realizo la insercion del curso");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DbException("El identificador de la ruta ya est?? en uso o el codigo de carrera no existe");
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

    public void updateRuta(Ruta newRutas) throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        PreparedStatement toDo = null;
        try {
            toDo = conexion.prepareCall(UPDATE_RUTAS);
            toDo.setInt(1, newRutas.getId());
            toDo.setInt(2, newRutas.getHorarioId().getId());
            toDo.setInt(3, newRutas.getCiudadDestino().getId());
            toDo.setInt(4, newRutas.getCiudadOrigen().getId());
            toDo.setDouble(5, newRutas.getPrecio());
            toDo.setDouble(6, newRutas.getPorcentajeDescuento());

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

    public Ruta getRuta(int id) throws GeneralException, DbException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se pudo localizar el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        ResultSet rs = null;
        Ruta ruta = null;
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(GET_RUTAS);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.setInt(2, id);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);

            if (rs.next()) {
                //Obtenci??n de los datos de ciudad origen
                Ciudad ciudadOrigen = new Ciudad();
                ciudadOrigen.setId(rs.getInt("id_origen"));
                ciudadOrigen.setNombre(rs.getString("nombre_origen"));

                //Obtenci??n de los datos de ciudad destino
                Ciudad ciudadDestino = new Ciudad();
                ciudadDestino.setId(rs.getInt("id_destino"));
                ciudadDestino.setNombre(rs.getString("nombre_destino"));

                //Obtenci??n de los datos del horario
                Horario horario = new Horario();
                horario.setId(rs.getInt("id_horario"));
                horario.setDiaSemana(rs.getString("dia_semana"));
                horario.setHoraLlegada(rs.getInt("hora_llegada"));

                //Creaci??n de la ruta 
                ruta = new Ruta(rs.getInt("id_ruta"), rs.getDouble("precio"),
                        rs.getDouble("porcentaje_descuento"), ciudadOrigen,
                        ciudadDestino, horario
                );
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

        if (ruta == null) {
            throw new DbException("El curso no existe");
        }

        return ruta;
    }

    public Collection listar_rutas() throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GeneralException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Ruta Ruta = null;
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(LISTAR_RUTAS);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);

            while (rs.next()) {
                //Obtenci??n de los datos de ciudad origen
                Ciudad ciudadOrigen = new Ciudad();
                ciudadOrigen.setId(rs.getInt("id_origen"));
                ciudadOrigen.setNombre(rs.getString("nombre_origen"));

                //Obtenci??n de los datos de ciudad destino
                Ciudad ciudadDestino = new Ciudad();
                ciudadDestino.setId(rs.getInt("id_destino"));
                ciudadDestino.setNombre(rs.getString("nombre_destino"));

                //Obtenci??n de los datos del horario
                Horario horario = new Horario();
                horario.setId(rs.getInt("id_horario"));
                horario.setDiaSemana(rs.getString("dia_semana"));
                horario.setHoraLlegada(rs.getInt("hora_llegada"));

                //Creaci??n de la ruta 
                Ruta = new Ruta(rs.getInt("id_ruta"), rs.getDouble("precio"),
                        rs.getDouble("porcentaje_descuento"), ciudadOrigen,
                        ciudadDestino, horario
                );
                coleccion.add(Ruta); //Se a??ade la ruta al array
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

    public void deleteRuta(int id) throws GeneralException, DbException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        PreparedStatement toDo = null;
        try {
            toDo = conexion.prepareStatement(DELETE_RUTAS);
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
