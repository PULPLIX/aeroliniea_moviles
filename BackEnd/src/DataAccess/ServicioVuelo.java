package DataAccess;

import Exceptions.DbException;
import Exceptions.GeneralException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import logic.Avion;
import logic.Ciudad;
import logic.Horario;
import logic.Ruta;
import logic.Vuelo;
import oracle.jdbc.OracleTypes;

public class ServicioVuelo extends Servicio {

    private static final String INSERCION_VUELOS = "{call INSERCION_VUELOS(?,?,?,?,?)}";
    private static final String UPDATE_VUELO = "{call UPDATE_VUELO(?,?,?,?,?,?)}";
    private static final String GET_VUELO = "{?=call GET_VUELO(?)}";
    private static final String LISTAR_VUELOS = "{?=call LISTAR_VUELOS()}";
    private static final String DELETE_VUELO = "{call DELETE_VUELO(?)}";
    private static final String FILTRAR_VUELO = "{?=call FILTRAR_VUELO(?,?,?,?,?)}";
    private static final String FILAS_OCUPADAS = "{?=call FILAS_OCUPADAS(?)}";
    private static final String COLUMNASS_OCUPADAS_X_ASIENTO = "{?=call COLUMNASS_OCUPADAS_X_ASIENTO(?,?)}";

    private static ServicioVuelo serviceVuelo;

    private ServicioVuelo() {

    }

    public static ServicioVuelo getSingletonInstance() throws GeneralException {
        if (serviceVuelo == null) {
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
            toDo = conexion.prepareCall(INSERCION_VUELOS);
            toDo.setInt(1, newVuelo.getModalidad());
            toDo.setInt(2, newVuelo.getDuracion());
            toDo.setInt(3, newVuelo.getRutaId().getId());
            toDo.setInt(4, newVuelo.getAvionId().getId());
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String fechaAux = dateFormat.format(newVuelo.getFecha());
            toDo.setString(5, fechaAux);

            boolean resultado = toDo.execute();
            if (resultado == true) {
                throw new DbException("No se realizo la insercion del curso");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DbException("El identificador de curso ya está en uso o el codigo de carrera no existe");
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
            toDo.setInt(4, newVuelo.getRutaId().getId());
            toDo.setInt(5, newVuelo.getAvionId().getId());
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String fechaAux = dateFormat.format(newVuelo.getFecha());
            toDo.setString(6, fechaAux);

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
                //Obtención de los datos de ciudad origen
                Ciudad ciudadOrigen = new Ciudad();
                ciudadOrigen.setId(rs.getInt("id_origen"));
                ciudadOrigen.setNombre(rs.getString("nombre_origen"));

                //Obtención de los datos de ciudad destino
                Ciudad ciudadDestino = new Ciudad();
                ciudadDestino.setId(rs.getInt("id_destino"));
                ciudadDestino.setNombre(rs.getString("nombre_destino"));

                //Obtención de los datos del horario
                Horario horario = new Horario();
                horario.setId(rs.getInt("id_horario"));
                horario.setDiaSemana(rs.getString("dia_semana"));
                horario.setHoraLlegada(rs.getInt("hora_llegada"));

                //Creación de la ruta 
                Ruta ruta = new Ruta(rs.getInt("id_ruta"), rs.getDouble("precio"),
                        rs.getDouble("porcentaje_descuento"), ciudadOrigen,
                        ciudadDestino, horario
                );

                Avion avion = new Avion(rs.getInt("id_avion"), rs.getString("tipo"),
                        rs.getInt("capacidad"), rs.getInt("anio"),
                        rs.getString("marca"), rs.getInt("asientos_fila"),
                        rs.getInt("cantidad_filas"));

                Vuelo = new Vuelo(rs.getInt("id_vuelo"), rs.getInt("modalidad"),
                        rs.getInt("duracion"), rs.getDate("fecha"),
                        avion, ruta);
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

    public Collection listar_vuelos() throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException ex) {
            throw new GeneralException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Vuelo vuelo = null;
        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(LISTAR_VUELOS);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);

            while (rs.next()) {
                //Obtención de los datos de ciudad origen
                Ciudad ciudadOrigen = new Ciudad();
                ciudadOrigen.setId(rs.getInt("id_origen"));
                ciudadOrigen.setNombre(rs.getString("nombre_origen"));

                //Obtención de los datos de ciudad destino
                Ciudad ciudadDestino = new Ciudad();
                ciudadDestino.setId(rs.getInt("id_destino"));
                ciudadDestino.setNombre(rs.getString("nombre_destino"));

                //Obtención de los datos del horario
                Horario horario = new Horario();
                horario.setId(rs.getInt("id_horario"));
                horario.setDiaSemana(rs.getString("dia_semana"));
                horario.setHoraLlegada(rs.getInt("hora_llegada"));

                //Creación de la ruta 
                Ruta ruta = new Ruta(rs.getInt("id_ruta"), rs.getDouble("precio"),
                        rs.getDouble("porcentaje_descuento"), ciudadOrigen,
                        ciudadDestino, horario
                );

                Avion avion = new Avion(rs.getInt("id_avion"), rs.getString("tipo"),
                        rs.getInt("capacidad"), rs.getInt("anio"),
                        rs.getString("marca"), rs.getInt("asientos_fila"),
                        rs.getInt("cantidad_filas"));

                vuelo = new Vuelo(rs.getInt("id_vuelo"), rs.getInt("modalidad"),
                        rs.getInt("duracion"), rs.getDate("fecha"),
                        avion, ruta);
                coleccion.add(vuelo);
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

    public ArrayList filtrarVuelo(String Modalidad, String idOrigen, String idDestino, String fechaI, String fechaF) throws GeneralException, DbException {

        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se pudo localizar el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        ResultSet rs = null;
        Vuelo Vuelo = null;
        ArrayList coleccion = new ArrayList();

        CallableStatement toDo = null;

        try {
            toDo = conexion.prepareCall(FILTRAR_VUELO);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.setString(2, Modalidad);
            toDo.setString(3, idOrigen);
            toDo.setString(4, idDestino);
            toDo.setString(5, fechaI);
            toDo.setString(6, fechaF);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);

            while (rs.next()) {
                //Obtención de los datos de ciudad origen
                Ciudad ciudadOrigen = new Ciudad();
                ciudadOrigen.setId(rs.getInt("id_origen"));
                ciudadOrigen.setNombre(rs.getString("nombre_origen"));

                //Obtención de los datos de ciudad destino
                Ciudad ciudadDestino = new Ciudad();
                ciudadDestino.setId(rs.getInt("id_destino"));
                ciudadDestino.setNombre(rs.getString("nombre_destino"));

                //Obtención de los datos del horario
                Horario horario = new Horario();
                horario.setId(rs.getInt("id_horario"));
                horario.setDiaSemana(rs.getString("dia_semana"));
                horario.setHoraLlegada(rs.getInt("hora_llegada"));

                //Creación de la ruta 
                Ruta ruta = new Ruta(rs.getInt("id_ruta"), rs.getDouble("precio"),
                        rs.getDouble("porcentaje_descuento"), ciudadOrigen,
                        ciudadDestino, horario
                );

                Avion avion = new Avion(rs.getInt("id_avion"), rs.getString("tipo"),
                        rs.getInt("capacidad"), rs.getInt("anio"),
                        rs.getString("marca"), rs.getInt("asientos_fila"),
                        rs.getInt("cantidad_filas"));

                Vuelo = new Vuelo(rs.getInt("id_vuelo"), rs.getInt("modalidad"),
                        rs.getInt("duracion"), rs.getDate("fecha"),
                        avion, ruta);
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

        if (Vuelo == null) {
            throw new DbException("El curso no existe");
        }

        return coleccion;
    }

    public HashMap<Integer, ArrayList<Integer>> getAsientosOcupados(int id_vuelo) throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }

        ResultSet rs = null;
        Vuelo Vuelo = null;
        HashMap<Integer, ArrayList<Integer>> filasOcupadas = new HashMap<>();

        CallableStatement toDo = null;
        try {
            //Se prepara la cunsulta para saber cuales son las filas ocupadas
            toDo = conexion.prepareCall(FILAS_OCUPADAS);
            //Se setean los parámetros necesarios para la consulta
            toDo.registerOutParameter(1, OracleTypes.CURSOR);//El cursor resultante de la consulta
            toDo.setString(2, Integer.toString(id_vuelo));//El id del vuelo
            toDo.execute();
            //Se obtiene el resultado de la consulta
            rs = (ResultSet) toDo.getObject(1);

            while (rs.next()) {
                filasOcupadas.put(rs.getInt("fila_asiento"), new ArrayList<Integer>());//Se agregan cada una de las filas ocupadas a un array
            }
            for (Integer i : filasOcupadas.keySet()) {
                toDo = null;
                toDo = conexion.prepareCall(COLUMNASS_OCUPADAS_X_ASIENTO);
                toDo.registerOutParameter(1, OracleTypes.CURSOR);//El cursor resultante de la consulta
                toDo.setString(2, Integer.toString(i));//La fila de la que se quiere saber los asientos que están ocupados
                toDo.setString(3, Integer.toString(id_vuelo));//El id del vuelo
                toDo.execute();
                //Se obtiene el resultado de la consulta
                rs = (ResultSet) toDo.getObject(1);
                while (rs.next()) {
                    int numCol = rs.getInt("columna_asiento");
                    filasOcupadas.get(i).add(numCol);
                }
            }
        } catch (SQLException e) {
            throw new GeneralException("Sentencia no valida");
        } finally {
            try {
                if (toDo != null) {
                    toDo.close();
                }
                desconectar();
                return filasOcupadas;
            } catch (SQLException e) {
                throw new GeneralException("Datos invalidos o nulos");
            }
        }
    }

}
