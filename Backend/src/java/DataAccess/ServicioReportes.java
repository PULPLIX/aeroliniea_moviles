package DataAccess;

import Exceptions.DbException;
import Exceptions.GeneralException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.Pair;
import logic.Tiquete;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author david
 */
public class ServicioReportes extends Servicio {

    private static final String FACTURADOXANIO = "{?=call facturadoPorAnio()}";
    private static final String FACTURADO12MESES = "{?=call facturado12Meses(?,?)}";
    private static final String TOP5RUTAS = "{?=call top5Rutas()}";

    private static ServicioReportes serviceReporte;

    private ServicioReportes() {

    }

    public static ServicioReportes getSingletonInstance() throws GeneralException {
        if (serviceReporte == null) {
            serviceReporte = new ServicioReportes();
        }

        return serviceReporte;

    }

    public ArrayList<String> getRangoFechas() {
        ArrayList<String> intervalo = new ArrayList<String>();
        LocalDate currentdate = LocalDate.now();
        Month currentMonth = currentdate.getMonth();
        int mesFin = currentMonth.getValue();

        int anioIni = currentdate.getYear();
        int anioFin = currentdate.getYear();

        int mesIni = 1;
        if (mesFin <= 11) {
            mesIni = 12 - (11 - mesIni);
            anioIni -= 1;
            mesFin += 1;
        } else {
            mesFin = 1;
            anioIni += 1;
        }

        String fechaIni = "01/" + String.valueOf(mesIni) + "/" + String.valueOf(anioIni);
        String fechaFin = "01/" + String.valueOf(mesFin) + "/" + String.valueOf(anioFin);

        intervalo.add(fechaIni);
        intervalo.add(fechaFin);
        return intervalo;
    }

    public ArrayList<Pair<Integer, String>> ingresosUltimos12Meses() throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        CallableStatement toDo = null;
        ResultSet rs = null;
        ArrayList<Pair<Integer, String>> dataSet = new ArrayList<Pair<Integer, String>>();

        try {
            ArrayList<String> rangoFechas = getRangoFechas();

            toDo = conexion.prepareCall(FACTURADO12MESES);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.setString(2, rangoFechas.get(0));
            toDo.setString(3, rangoFechas.get(1));
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);

            while (rs.next()) {
                double totalMes = rs.getDouble("total");
                String mes = rs.getString("Mes");
                Pair set = new Pair(totalMes, mes);
                dataSet.add(set);
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
        return dataSet;
    }

    public ArrayList<Pair<Integer, String>> facturadoXAnio() throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        CallableStatement toDo = null;
        ResultSet rs = null;
        ArrayList<Pair<Integer, String>> dataSet = new ArrayList<Pair<Integer, String>>();

        try {

            toDo = conexion.prepareCall(FACTURADOXANIO);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);

            while (rs.next()) {
                double totalMes = rs.getDouble("total");
                String mes = rs.getString("Mes");
                Pair set = new Pair(totalMes, mes);
                dataSet.add(set);
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
        return dataSet;
    }

        public ArrayList<Pair<Integer, String>> top5Rutas() throws GeneralException, DbException {
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GeneralException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new DbException("No se puede establecer una conexion con la base de datos");
        }
        CallableStatement toDo = null;
        ResultSet rs = null;
        ArrayList<Pair<Integer, String>> dataSet = new ArrayList<Pair<Integer, String>>();

        try {

            toDo = conexion.prepareCall(TOP5RUTAS);
            toDo.registerOutParameter(1, OracleTypes.CURSOR);
            toDo.execute();
            rs = (ResultSet) toDo.getObject(1);

            while (rs.next()) {
                double cant = rs.getInt("cant");
                String ruta = rs.getString("ciudad_origen") +" / " +rs.getString("ciudad_destino")  ;
                Pair set = new Pair(cant, ruta);
                dataSet.add(set);
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
        return dataSet;
    }
}