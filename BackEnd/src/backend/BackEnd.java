
package backend;

import Exceptions.DbException;
import Exceptions.GeneralException;
import DataAccess.ServicioCarrera;
import DataAccess.ServicioCurso;
import logic.Usuario;
import DataAccess.ServicioUsuario;



public class BackEnd {


    public static void main(String[] args) throws GeneralException, DbException {
        
        Usuario user = new Usuario("207970180","1234",0);
        
        ServicioUsuario servUsuario = ServicioUsuario.getSingletonInstance();
              
        
        System.out.println(servUsuario.getUsuario("207970180").toString()); 
        
//        Curso curse = new Curso("EIF215", "Inge 3", "EIF", 3, 2, 7, 4);
//
//        Curso curseModifier = new Curso("EIF213", "Inge 2", "EIF", 2, 4, 5, 4);
//
        ServicioCurso servCurse = ServicioCurso.getSingletonInstance();
//
//        System.out.println(curse.toString());
//
//        servCurse.insercionCurso(curse);
//        servCurse.updateCurso(curseModifier);
//
//        servCurse.deleteCurso("EIF215");
        System.out.println(servCurse.getCurso("EIF213").toString());
        for (Object curs : servCurse.listar_curso()) {
            System.out.println(curs.toString());
        }

        
        System.out.println("=====================================================");

//        Carrera carrera = new Carrera("SE", "Ing. Sistemas", "Bachiller");
//
//        Carrera carreraModifier = new Carrera("EI", "Ing. Sistemas2", "Licenciatura");
//
        ServicioCarrera servCarrera = ServicioCarrera.getSingletonInstance();
//
//        System.out.println("New: " + carrera.toString());
//        servCarrera.insercionCarrera(carrera);
//        System.out.println("GET: " + servCarrera.getCarrera("SE").toString());
//        servCarrera.updateCarrera(carreraModifier);
//
//        servCarrera.deleteCarrera("SE");
        for (Object carreras : servCarrera.listar_carrera()) {
            System.out.println(carreras.toString());
        }
    }
    
}
