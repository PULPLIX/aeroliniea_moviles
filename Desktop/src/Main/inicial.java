/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.ControllerUsuario;
import Model.ModelUsuario;
import View.ViewLogin;

/**
 *
 * @author david
 */
public class inicial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Model.ModelUsuario AplicationModel = new ModelUsuario();
        ViewLogin AplicationView = new ViewLogin();
        ControllerUsuario AplicationController = new ControllerUsuario(AplicationModel, AplicationView);

        AplicationView.setVisible(true);
        AplicationView.setVisible(true);
    }

}
