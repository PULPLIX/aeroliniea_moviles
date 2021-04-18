package backend;

import DataAccess.servicioReportes;
import Exceptions.DbException;
import Exceptions.GeneralException;
import java.util.ArrayList;
import javafx.util.Pair;

public class BackEnd {

    public static void main(String[] args) throws GeneralException, DbException, Exception {
        System.out.println(" ##### ----> BACKEND EJECUTADO <---- #### ");

        servicioReportes sReportes = servicioReportes.getSingletonInstance();
        ArrayList<Pair<Integer, String>> ultimos12 = sReportes.ingresosUltimos12Meses();
        ArrayList<Pair<Integer, String>> facturadoAnio = sReportes.facturadoXAnio();
        ArrayList<Pair<Integer, String>> top5Rutas = sReportes.top5Rutas();

        System.out.print("\n\tTOTAL FACTURADO EN EL AÑO\n");
        for (Pair<Integer, String> pair : facturadoAnio) { // iterating on list of 'Pair's
            System.out.print(pair.getKey() + "=> " + pair.getValue() + "\n");
        }
        System.out.print("\n\tTOP 5 RUTAS MAS VENDIDAS\n");
        for (Pair<Integer, String> pair : top5Rutas) { // iterating on list of 'Pair's
            System.out.print(pair.getKey() + "=> " + pair.getValue() + "\n");
        }
        
        System.out.print("\n\tULTIMOS 12 MESES\n");
        for (Pair<Integer, String> pair : ultimos12) { // iterating on list of 'Pair's
            System.out.print(pair.getKey() + "=> " + pair.getValue() + "\n");
        }
    }

}
