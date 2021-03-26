
package backend;

import Exceptions.DbException;
import Exceptions.GeneralException;
//import DataAccess.ServicioCarrera;
import DataAccess.ServicioAviones;
import DataAccess.ServicioHorario;
import DataAccess.ServicioUsuario;
import logic.Avion;
import logic.Horario;
//import DataAccess.ServicioUsuario;



public class BackEnd {


    public static void main(String[] args) throws GeneralException, DbException {
        
//        Usuario user = new Usuario("207970180","1234",0);
//        
//        ServicioUsuario servUsuario = ServicioUsuario.getSingletonInstance();
              
        
//        System.out.println(servUsuario.getUsuario("207970180").toString()); 
        
//        Curso curse = new Curso("EIF215", "Inge 3", "EIF", 3, 2, 7, 4);
//
//        Curso curseModifier = new Curso("EIF213", "Inge 2", "EIF", 2, 4, 5, 4);
//
//        ServicioCurso servCurse = ServicioCurso.getSingletonInstance();
//
//        System.out.println(curse.toString());
//
//        servCurse.insercionCurso(curse);
//        servCurse.updateCurso(curseModifier);
//
//        servCurse.deleteCurso("EIF215");
//        System.out.println(servCurse.getCurso("EIF213").toString());
//        for (Object curs : servCurse.listar_curso()) {
//            System.out.println(curs.toString());
//        }

        
        System.out.println("=====================================================");
        System.out.println("==========================AVIONES===========================");

        ServicioAviones servAviones = ServicioAviones.getSingletonInstance();
        Avion avioncito = new Avion(360,"Volador",128,2021,"Veloz",5,26);
        servAviones.insercionAviones(avioncito);
        System.out.println("GET: " + servAviones.getAvion(1).toString());
        for (Object avion : servAviones.listar_aviones()){
            System.out.println(avion.toString());
        }
        ServicioUsuario servUs = ServicioUsuario.getSingletonInstance();
        if(servUs.validaUsario("12", "1234")){
           System.out.println("TODO CORRECTO Y YO QUE ME ALEGRO");
        }
        
        //servAviones.deleteAvion(3);
        
        System.out.println("=====================================================");
        System.out.println("==========================HORARIOS===========================");

        //ServicioHorario servHorario = ServicioHorario.getSingletonInstance();
        //Horario horario = new Horario(12,"Lunes",2);
        //servHorario.insercionHorario(horario);
        //System.out.println("GET: " + servHorario.getHorario(2).toString());
//        for (Object horar : servHorario.listar_horario()) {
//            System.out.println(horar.toString());
//        }

//        Carrera carrera = new Carrera("SE", "Ing. Sistemas", "Bachiller");
//
//        Carrera carreraModifier = new Carrera("EI", "Ing. Sistemas2", "Licenciatura");
//
//        ServicioCarrera servCarrera = ServicioCarrera.getSingletonInstance();
//
//        System.out.println("New: " + carrera.toString());
//        servCarrera.insercionCarrera(carrera);
//        System.out.println("GET: " + servCarrera.getCarrera("SE").toString());
//        servCarrera.updateCarrera(carreraModifier);
//
//        servCarrera.deleteCarrera("SE");
//        for (Object carreras : servCarrera.listar_carrera()) {
//            System.out.println(carreras.toString());
//        }
    }
    
}
