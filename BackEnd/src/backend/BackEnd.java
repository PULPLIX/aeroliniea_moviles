package backend;

import Exceptions.DbException;
import Exceptions.GeneralException;
//import DataAccess.ServicioCarrera;
import DataAccess.ServicioAviones;
import DataAccess.ServicioHorario;
import DataAccess.ServicioUsuario;
import logic.Avion;
import logic.Horario;
import logic.Usuario;
//import DataAccess.ServicioUsuario;

public class BackEnd {

    public static void main(String[] args) throws GeneralException, DbException {
        System.out.println(" ##### ----> BACKEND EJECUTADO <---- #### ");

//        System.out.println("=====================================================");
//        System.out.println("==========================USUARIO===========================");
//
//        ServicioUsuario servUsuario = ServicioUsuario.getSingletonInstance();
//        Usuario usuario = servUsuario.getUsuario("12");
//
//        if(servUsuario.validaUsuario("12", "1234")){
//           System.out.println("TODO CORRECTO Y YO QUE ME ALEGRO " + usuario.getNombre());
//        }
//        for (Object Usuario : servUsuario.listar_usuario()){
//            System.out.println(Usuario.toString());
//        }
//        
//        System.out.println("=====================================================");
//        System.out.println("==========================AVIONES===========================");
//
//        ServicioAviones servAviones = ServicioAviones.getSingletonInstance();
//        Avion avioncito = new Avion(360,"Volador",128,2021,"Veloz",5,26);
//        servAviones.insercionAviones(avioncito);
//        System.out.println("GET: " + servAviones.getAvion(1).toString());
//        for (Object avion : servAviones.listar_aviones()){
//            System.out.println(avion.toString());
//        }
        //servAviones.deleteAvion(3);
//        System.out.println("=====================================================");
//        System.out.println("==========================HORARIOS===========================");
 //       ServicioHorario servHorario = ServicioHorario.getSingletonInstance();
        //Horario horario = new Horario(12, "Lunes", 2);
        //servHorario.insercionHorario(horario);
//        System.out.println("GET: " + servHorario.getHorario(2).toString());
        //     servHorario.deleteHorario(2);
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
